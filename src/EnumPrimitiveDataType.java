
public enum EnumPrimitiveDataType {
    //https://medium.com/omarelgabrys-blog/primitive-data-types-in-c-vs-java-5b8a597eef05
    //int, object, short, char, float, double, char, bool.

    //TODO: object? ask someone who knows c#


    INT("int"),
    SHORT("short"),
    CHAR("char"),
    FLOAT("float"),
    DOUBLE("double"),
    BOOLEAN("bool");


    private   String c;

    EnumPrimitiveDataType(String c) {
        this.c = c;
    }

    public String getString() {
        return c;
    }


    String getDataType() {
        return c;
    }

}

