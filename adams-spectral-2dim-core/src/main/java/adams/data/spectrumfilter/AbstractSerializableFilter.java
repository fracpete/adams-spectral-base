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
 * AbstractSerializableFilter.java
 * Copyright (C) 2009-2017 University of Waikato, Hamilton, New Zealand
 */

package adams.data.spectrumfilter;

import adams.core.SerializableObject;
import adams.core.SerializableObjectHelper;
import adams.core.io.PlaceholderFile;
import adams.data.filter.AbstractFilter;
import adams.data.spectrum.Spectrum;

/**
 * Abstract cleaner that can serialize its setup to disk and also load it
 * from there.
 *
 * @author  fracpete (fracpete at waikato dot ac dot nz)
 * @version $Revision: 2242 $
 */
public abstract class AbstractSerializableFilter
  extends AbstractFilter<Spectrum>
  implements SerializableObject {

  /** for serialization. */
  private static final long serialVersionUID = -7799719744490686394L;

  /** the file to serialize the setup to .*/
  protected PlaceholderFile m_SerializationFile;

  /** whether to override the file to serialize the setup to .*/
  protected boolean m_OverrideSerializationFile;

  /** the helper class for serializing. */
  protected SerializableObjectHelper m_SerializableObjectHelper;

  /**
   * Adds options to the internal list of options.
   */
  @Override
  public void defineOptions() {
    super.defineOptions();

    m_OptionManager.add(
	    "serialization-file", "serializationFile",
	    new PlaceholderFile("."));

    m_OptionManager.add(
	    "override-serialized-file", "overrideSerializedFile",
	    false);
  }

  /**
   * Initializes the members.
   */
  @Override
  protected void initialize() {
    super.initialize();

    m_SerializableObjectHelper = new SerializableObjectHelper(this);
  }

  /**
   * Resets the scheme.
   */
  @Override
  public void reset() {
    super.reset();

    m_SerializableObjectHelper.reset();
  }

  /**
   * Sets the file to serialize to.
   *
   * @param value	the file
   */
  public void setSerializationFile(PlaceholderFile value) {
    m_SerializationFile = value;
    reset();
  }

  /**
   * Returns the current file to serialize to.
   *
   * @return		the file
   */
  public PlaceholderFile getSerializationFile() {
    return m_SerializationFile;
  }

  /**
   * Returns the tip text for this property.
   *
   * @return 		tip text for this property suitable for
   * 			displaying in the GUI or for listing the options.
   */
  public String serializationFileTipText() {
    return "The file to serialize the generated internal model to.";
  }

  /**
   * Sets whether to override an existing serialized setup.
   *
   * @param value	true if to override existing setup
   */
  public void setOverrideSerializedFile(boolean value) {
    m_OverrideSerializationFile = value;
    reset();
  }

  /**
   * Returns whether to override an existing serialized setup.
   *
   * @return		true if existing file is ignored
   */
  public boolean getOverrideSerializedFile() {
    return m_OverrideSerializationFile;
  }

  /**
   * Returns the tip text for this property.
   *
   * @return 		tip text for this property suitable for
   * 			displaying in the GUI or for listing the options.
   */
  public String overrideSerializedFileTipText() {
    return
        "If set to true, then any serialized file will be ignored and the "
      + "setup for serialization will be regenerated.";
  }

  /**
   * Sets whether the setup was loaded/generated.
   *
   * @param value	true if loaded/generated
   */
  public void setSetupLoadedOrGenerated(boolean value) {
    m_SerializableObjectHelper.setSetupLoadedOrGenerated(value);
  }

  /**
   * Returns whether the setup was loaded/generated.
   *
   * @return		true if loaded/generated
   */
  public boolean isSetupLoadedOrGenerated() {
    return m_SerializableObjectHelper.isSetupLoadedOrGenerated();
  }

  /**
   * Loads the serialized data on demand in addition to the checks.
   *
   * @param data	the data to process
   */
  @Override
  protected void checkData(Spectrum data) {
    super.checkData(data);

    try {
      m_SerializableObjectHelper.loadSetup();
    }
    catch (Exception e) {
      throw new IllegalStateException("Failed to load setup: " + this, e);
    }
  }
  
  /**
   * Frees up memory in a "destructive" non-reversible way.
   * <br><br>
   * Destroys the {@link SerializableObjectHelper} instance.
   */
  @Override
  public void destroy() {
    if (m_SerializableObjectHelper != null) {
      m_SerializableObjectHelper.destroy();
      m_SerializableObjectHelper = null;
    }
    
    super.destroy();
  }
}
