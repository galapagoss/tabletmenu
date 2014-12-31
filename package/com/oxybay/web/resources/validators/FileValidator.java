/**
 * FileExtensionValidator.java
 * 15/lug/2012
 * @author Gaetano
 * IMPORTANT - Remember to provide specific GETTER/SETTER (xxxFileName & xxxFileLogo)
 */

package com.oxybay.web.resources.validators;

import java.io.File;
import java.util.Set;

import org.apache.struts2.StrutsConstants;

import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.util.TextParseUtil;
import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

/**
 *IMPORTANT - Remember to provide specific GETTER/SETTER (xxxFileName & xxxFileLogo)
 */
public class FileValidator extends FieldValidatorSupport {
	
	/* allowed Extension */
	private String allowedExtensions = "";
	/* allowed Content Types */
	private String allowedContents = "";
	/* max file size */
	private long maxSize = 0;
	/* default max size */
	private long defaultMaxSize = 0;

	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.validator.Validator#validate(java.lang.Object)
	 */
	@Override
	public void validate(Object arg0) throws ValidationException {
		File file = (File)getFieldValue(getFieldName(), arg0);
		if (file!=null) {
			long sizeLimit = Math.max(maxSize, defaultMaxSize);
			if (sizeLimit>0 && file.length()>sizeLimit) {
				addFieldError(getFieldName(), arg0);
				return;
			}
			if(!allowedExtensions.equals("")) {
				String filename = (String)getFieldValue(getFieldName()+"FileName", arg0);
				String ext = (filename!=null && filename.lastIndexOf(".")>=0) ? filename.substring(filename.lastIndexOf(".")) : "";
				Set<String> allowedExt = TextParseUtil.commaDelimitedStringToSet(allowedExtensions);
				if (ext.equals("") || !allowedExt.contains(ext)) {
					addFieldError(getFieldName(), arg0);
					return;
				}
			}
			if(!allowedContents.equals("")) {
				String content = (String)getFieldValue(getFieldName()+"ContentType", arg0);
				Set<String> allowedTypes = TextParseUtil.commaDelimitedStringToSet(allowedContents);
				if (content==null || content.equals("") || !allowedTypes.contains(content)) {
					addFieldError(getFieldName(), arg0);
					return;
				}
			}
		}
		
	}


	/**
	 * @return the allowedExtensions
	 */
	public String getAllowedExtensions() {
		return allowedExtensions;
	}


	/**
	 * @param allowedExtensions the allowedExtensions to set
	 */
	public void setAllowedExtensions(String allowedExtensions) {
		this.allowedExtensions = allowedExtensions;
	}


	/**
	 * @return the allowedContents
	 */
	public String getAllowedContents() {
		return allowedContents;
	}


	/**
	 * @param allowedContents the allowedContents to set
	 */
	public void setAllowedContents(String allowedContents) {
		this.allowedContents = allowedContents;
	}


	/**
	 * @return the maxSize
	 */
	public long getMaxSize() {
		return maxSize;
	}


	/**
	 * @param maxSize the maxSize to set
	 */
	public void setMaxSize(long maxSize) {
		this.maxSize = maxSize;
	}

	@Inject(StrutsConstants.STRUTS_MULTIPART_MAXSIZE)
  public void setMaxSize(String maxSize) {
      this.defaultMaxSize = Long.parseLong(maxSize);
  }
}
