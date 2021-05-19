package mementoodeviornek1;

public class Main {
    public static void main(String[] args) 
    {
        Article article = new Article(1, "Makalem");
        article.setContent("Orijinal icerik");      //original content
        System.out.println(article);
         
        ArticleMemento memento = article.createMemento();   //created immutable memento
         
        article.setContent("Baska icerik");      
        System.out.println(article);
         
        article.restore(memento);       //UNDO change
        System.out.println(article);    //original content
    }
}
