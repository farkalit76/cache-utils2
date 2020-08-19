package ae.gov.sdg.paperless.platform.common.model.components;

import javax.validation.constraints.NotBlank;

public class DataContainer
{
	@NotBlank
	private String data;

	public String getData() {
		return data;
	}

	public void setData(final String data) {
		this.data = data;
	}
}
