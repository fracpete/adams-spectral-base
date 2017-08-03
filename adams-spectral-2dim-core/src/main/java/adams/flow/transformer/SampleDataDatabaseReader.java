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
 * SampleDataDatabaseReader.java
 * Copyright (C) 2012-2016 University of Waikato, Hamilton, New Zealand
 */
package adams.flow.transformer;

import adams.core.ShallowCopySupporter;
import adams.flow.core.Actor;
import adams.flow.core.InputConsumer;
import adams.flow.core.OutputProducer;

/**
 * Indicator interface for database readers for sample data.
 * 
 * @author  fracpete (fracpete at waikato dot ac dot nz)
 * @version $Revision: 1730 $
 */
public interface SampleDataDatabaseReader
  extends ShallowCopySupporter<Actor>, InputConsumer, OutputProducer {

}