/*
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * SetSampleDataValueTest.java
 * Copyright (C) 2010 University of Waikato, Hamilton, New Zealand
 */

package adams.flow.transformer;

import adams.data.io.input.SimpleSpectrumReader;
import adams.data.report.DataType;
import adams.data.report.Field;
import adams.env.Environment;
import adams.flow.AbstractSpectrumFlowTest;
import adams.flow.condition.bool.Counting;
import adams.flow.control.ConditionalTee;
import adams.flow.control.Flow;
import adams.flow.control.Sequence;
import adams.flow.core.Actor;
import adams.flow.sink.DumpFile;
import adams.flow.source.FileSupplier;
import adams.test.TmpFile;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Tests the SetSampleDataValue actor.
 *
 * @author  fracpete (fracpete at waikato dot ac dot nz)
 * @version $Revision: 1865 $
 */
public class SetSampleDataValueTest
  extends AbstractSpectrumFlowTest {

  /**
   * Initializes the test.
   *
   * @param name	the name of the test
   */
  public SetSampleDataValueTest(String name) {
    super(name);
  }

  /**
   * Called by JUnit before each test method.
   *
   * @throws Exception if an error occurs
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();

    m_TestHelper.copyResourceToTmp("871553-nir.spec");
    m_TestHelper.deleteFileFromTmp("dumpfile.txt");
  }

  /**
   * Called by JUnit after each test method.
   *
   * @throws Exception	if tear-down fails
   */
  @Override
  protected void tearDown() throws Exception {
    m_TestHelper.deleteFileFromTmp("871553-nir.spec");
    m_TestHelper.deleteFileFromTmp("dumpfile.txt");

    super.tearDown();
  }

  /**
   * Used to create an instance of a specific actor.
   *
   * @return a suitably configured <code>Actor</code> value
   */
  @Override
  public Actor getActor() {
    FileSupplier sfs = new FileSupplier();
    sfs.setFiles(new adams.core.io.PlaceholderFile[]{new TmpFile("871553-nir.spec")});

    SpectrumFileReader sfr = new SpectrumFileReader();
    sfr.setReader(new SimpleSpectrumReader());

    SetSampleDataValue sv = new SetSampleDataValue();
    sv.setField(new Field("GLV2", DataType.NUMERIC));
    sv.setValue("100.0");

    GetSampleDataValue gv = new GetSampleDataValue();
    gv.setField(new Field("GLV2", DataType.NUMERIC));

    DumpFile df = new DumpFile();
    df.setAppend(true);
    df.setOutputFile(new TmpFile("dumpfile.txt"));

    Sequence seq = new Sequence();
    seq.setActors(new Actor[]{
	sv, gv, df
    });

    ConditionalTee ct = new ConditionalTee();
    Counting count = new Counting();
    count.setMaximum(5);
    count.setInterval(1);
    ct.setCondition(count);
    ct.add(0, seq);

    Flow flow = new Flow();
    flow.setActors(new Actor[]{sfs, sfr, ct});

    return flow;
  }

  /**
   * Performs a regression test, comparing against previously generated output.
   */
  public void testRegression() {
    performRegressionTest(
	    new TmpFile("dumpfile.txt"));
  }

  /**
   * Returns a test suite.
   *
   * @return		the test suite
   */
  public static Test suite() {
    return new TestSuite(SetSampleDataValueTest.class);
  }

  /**
   * Runs the test from commandline.
   *
   * @param args	ignored
   */
  public static void main(String[] args) {
    Environment.setEnvironmentClass(Environment.class);
    runTest(suite());
  }
}
