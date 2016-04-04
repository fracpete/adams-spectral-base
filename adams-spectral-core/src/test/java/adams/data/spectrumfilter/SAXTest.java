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

/**
 * SAXTest.java
 * Copyright (C) 2014 University of Waikato, Hamilton, New Zealand
 */
package adams.data.spectrumfilter;

import adams.data.filter.AbstractFilter;
import adams.env.Environment;
import junit.framework.Test;
import junit.framework.TestSuite;
import adams.data.spectrum.Spectrum;

/**
 * Test class for the SAX filter. Run from the command line with: <br><br>
 * java adams.data.filter.SAXTest
 *
 * @author  fracpete (fracpete at waikato dot ac dot nz)
 * @version $Revision: 7892 $
 */
public class SAXTest
  extends AbstractSpectrumFilterTestCase {

  /**
   * Constructs the test case. Called by subclasses.
   *
   * @param name 	the name of the test
   */
  public SAXTest(String name) {
    super(name);
  }

  /**
   * Returns the configured filter.
   *
   * @return		the filter
   */
  public AbstractFilter<Spectrum> getFilter() {
    return new SAX();
  }

  /**
   * Returns the filenames (without path) of the input data files to use
   * in the regression test.
   *
   * @return		the filenames
   */
  @Override
  protected String[] getRegressionInputFiles() {
    return new String[]{
	"872280-nir.spec",
	"872280-nir.spec",
	"872280-nir.spec",
    };
  }

  /**
   * Returns the setups to use in the regression test.
   *
   * @return		the setups
   */
  @Override
  protected AbstractFilter[] getRegressionSetups() {
    MultiFilter[]	result;
    RowNorm		row;
    SAX			sax;

    result = new MultiFilter[3];

    row = new RowNorm();
    
    result[0] = new MultiFilter();
    sax       = new SAX();
    result[0].setSubFilters(new AbstractFilter[]{
	row,
	sax
    });

    result[1] = new MultiFilter();
    sax       = new SAX();
    sax.setNumBins(20);
    sax.setNumWindows(20);
    result[1].setSubFilters(new AbstractFilter[]{
	row,
	sax
    });

    result[2] = new MultiFilter();
    sax       = new SAX();
    sax.setNumBins(5);
    sax.setNumWindows(5);
    result[2].setSubFilters(new AbstractFilter[]{
	row,
	sax
    });

    return result;
  }

  /**
   * Returns the test suite.
   *
   * @return		the suite
   */
  public static Test suite() {
    return new TestSuite(SAXTest.class);
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
