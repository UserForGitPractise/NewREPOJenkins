package web.selenium;

enum Enum1 {
    MONDAY{
        public void qwe(){
            System.out.println( "This is First Day of the week");
        }
    },
    TUESDAY{
        public void qwe(){
            System.out.println("This is Second Day of the week");
        }
    },
    WEDNESDAY{public void qwe(){
        System.out.println("This is First Day of the week");
    }}
    ;

    private String timing;
    //Enum1 (String time){
//        timing= time;
//    }
    public String getTiming (){
        return timing;
    }
    public abstract void qwe();
}
