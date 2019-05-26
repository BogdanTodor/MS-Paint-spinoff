class ShapeException extends Exception{
    public ShapeException(){
        super();
    }

    public ShapeException(String message){
        super(message);
    }

    public ShapeException(Throwable cause){
        super(cause);
    }

    public ShapeException(String message, Throwable cause){
        super(message, cause);
    }

}