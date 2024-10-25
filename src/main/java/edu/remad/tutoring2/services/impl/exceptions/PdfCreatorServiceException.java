package edu.remad.tutoring2.services.impl.exceptions;

public class PdfCreatorServiceException extends RuntimeException {

	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = -4721986460598298023L;
	
	/** 
	 * Creates Instance of {@link PdfCreatorServiceException}.
     */
    public PdfCreatorServiceException() {
        super();
    }

    /** 
     * Creates Instance of {@link PdfCreatorServiceException} with an error message.
     *
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
    public PdfCreatorServiceException(String message) {
        super(message);
    }

    /**
     * Creates Instance of {@link PdfCreatorServiceException} with an error message and cause.
     *
     * @param  message the detail message (which is saved for later retrieval
     *         by the {@link #getMessage()} method).
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A {@code null} value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     * @since  1.4
     */
    public PdfCreatorServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
