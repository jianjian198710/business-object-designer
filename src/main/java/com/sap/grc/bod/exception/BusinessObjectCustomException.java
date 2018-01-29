package com.sap.grc.bod.exception;

public class BusinessObjectCustomException extends RuntimeException
{
	private static final long serialVersionUID = 4690753617297794661L;

	private Integer errCode;
	private String errMessage;
	private Throwable errCause;

	public BusinessObjectCustomException( int code, String message, Throwable cause )
	{
		super(code + " : " + message, cause);
	}

	public BusinessObjectCustomException( ExceptionEnum exceptionEnum )
	{
		super(exceptionEnum.getCode() + " : " + exceptionEnum.getMessage());
		this.errCode = exceptionEnum.getCode();
		this.errMessage = exceptionEnum.getMessage();
	}

	public BusinessObjectCustomException( ExceptionEnum exceptionEnum, Throwable cause )
	{
		super(exceptionEnum.getCode() + " : " + exceptionEnum.getMessage(), null);
		this.errCode = exceptionEnum.getCode();
		this.errMessage = exceptionEnum.getMessage();
		this.errCause = cause;
	}

	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" Error Code: ");
		stringBuilder.append(this.errCode);
		stringBuilder.append(" Error Message: ");
		stringBuilder.append(this.errMessage);
		stringBuilder.append(" \n");

		if( this.errCause != null ) {
			stringBuilder.append("Stack Trace as following: \n");
			stringBuilder.append(this.errCause.getStackTrace());
		}
		return stringBuilder.toString();
	}

	public Integer getMessageCode()
	{
		return this.errCode;
	}

	public String getMessageContent()
	{
		return this.errMessage;
	}

	public enum ExceptionEnum
	{
		BusinessObject_isExisted(1001, "Business Object has been created"),
		BusinessObject_isNotExisted(1002, "Business Object is not existed"),
		BusinessObject_isNull(1003, "Business Object is null"),

		BusinessObjectField_isExisted(2001,"Business Object Field has been created"),
		BusinessObjectField_isNotExisted(2002,"Business Object Field is not existed"),
		BusinessObjectField_Name_isEmpty(2003,"Business Object Field name is empty"),
		BusinessObjectField_FieldName_isNotSame(2004, "Business Object Field Name is inconsistent"),
		BusinessObjectField_errInput(2005,"Business Object Field Name is error"),

		BusinessObjectFieldOption_IdAssigned(3001,"Option Id should not be assigned"),
		BusinessObjectFieldOption_DuplicatedId(3002,"Duplicated id exists in parameter"),
		BusinessObjectFieldOption_IdNotExisted(3003,"Business Object Field Option id is not existed"),
		BusinessObjectFieldOption_errInput(004,"Business Object Field Option parameter is error");
		
		private final String message;
		private final Integer code;

		ExceptionEnum( int code, String message )
		{
			this.message = message;
			this.code = code;
		}

		public String getMessage()
		{
			return this.message;
		}

		public Integer getCode()
		{
			return this.code;
		}

		public String toString()
		{
			return this.code + " : " + this.message;
		}
	}
}
