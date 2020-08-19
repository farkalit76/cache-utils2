package ae.gov.sdg.paperless.platform.common.processor.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TemplateBean {

	private String fileName;

	private String version;

	private String platform;
	
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 * @return
	 */
	public TemplateBean setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 * @return
	 */
	public TemplateBean setVersion(String version) {
		this.version = version;
		return this;
	}

	/**
	 * @return the platform
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * @param platform the platform to set
	 * @return
	 */
	public TemplateBean setPlatform(String platform) {
		this.platform = platform;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((platform == null) ? 0 : platform.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TemplateBean other = (TemplateBean) obj;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName)) {
			return false;
		}
		if (platform == null) {
			if (other.platform != null)
				return false;
		} else if (!platform.equals(other.platform)) {
			return false;
		}
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("fileName", fileName).append("version", version)
				.append("platform", platform).build();
	}

}
