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
 * SampleDataContainerDisplayIDGenerator.java
 * Copyright (C) 2016 University of Waikato, Hamilton, New Zealand
 */

package adams.gui.visualization.spectrum;

import adams.data.sampledata.SampleData;
import adams.gui.visualization.container.AbstractContainerDisplayStringGenerator;
import adams.gui.visualization.report.ReportContainer;

/**
 * Class for generating display IDs.
 *
 * @author  fracpete (fracpete at waikato dot ac dot nz)
 * @version $Revision: 1286 $
 */
public class SampleDataContainerDisplayIDGenerator
  extends AbstractContainerDisplayStringGenerator<ReportContainer> {

  /** for serialization. */
  private static final long serialVersionUID = -4131184984196361251L;

  /**
   * Returns the display ID for the spectrum.
   *
   * @param c		the spectrum to get the display ID for
   * @return		the ID
   */
  public String getDisplay(ReportContainer c) {
    if (c.getReport().hasValue(SampleData.SAMPLE_ID) && c.getReport().hasValue(SampleData.FORMAT))
      return c.getReport().getStringValue(SampleData.SAMPLE_ID) + "/" + c.getReport().getStringValue(SampleData.FORMAT);
    else
      return c.getID();
  }
}