# Memento-Design-Pattern
Hatırlayıcı(Memento) Tasarım Deseni 

Osman Bekir Erik
 

Memento, davranışsal tasarım kalıpları grubunda olan bir tasarım desenidir. Memento tasarım kalıbını hangi durumlarda kullanabiliriz sorusuyla başlayabiliriz. Memento tasarım kalıbı ile varlıkların o anki mevcut konumları saklanarak herhangi bir zaman dilimi içerisinde bir önceki hallerine döndürülmek istenen durumlarda kullanılır. 

Genel Sınıf Diyagramı
<img alt="memento genel uml diyagrami" src="https://github.com/osmnbkrerk/Memento-Design-Pattern/blob/main/resimler/memento_uml_diyagrami.png"></html>



Öncelikle Memento tasarım deseninin genel olarak nasıl işlediğinden kısa bir şekilde bahsedelim. Daha sonra ise örnekler üzerinden anlatmaya devam edeceğim. Ve böylelikle konu havada kalmamış olup mantığını anlamış olacağız.

Üstte gördüğümüz diyagramda Memento sınıfı için konuşacak olursak;
Memento, Bir nesnenin iç durumunu saklamak için kullanılır. Buradaki nesne, Originator sınıfının örneğidir. Ve ayrıca Originator ve CareTaker sınıfları ile ilişkisi vardır.

Originator sınıfı ise dahili durumun depolanabilmesi için Memento örneği oluşturur. Önceki duruma geri yüklemek için Memento'yu kullanır.

CareTaker, geri dönüş adımlarımızı Memento tipinden tutacak olan sınıftır.








Genel olarak Memento’nun nasıl çalıştığından bahsettik. Şimdi gelelim Memento’nun nerede ve ne zaman kullanılabileceğine. 

Memento tasarım modeli, gerçek dünyada neredeyse baktığımız her yerde bulunabilir. Bu model, nesnenin durumunun sürekli olarak değiştiği herhangi bir uygulamada kullanılabilir ve uygulamanın kullanıcısı herhangi bir noktada değişiklikleri geri almaya karar verebilir. 

Peki Memento Tasarım Deseni gerçek hayatta nerelerde kullanılabilir? 

Örneğin, televizyonunuzun ses kontrolünü düşünün. Eski bir CRT ekranınız veya son teknoloji ürünü bir akıllı OLED ekranınız olması fark etmez, ses seviyesini değiştirmenin temel davranışı aynı şekilde çalışır. 

Televizyon (veya alıcı, hoparlörler, vb.) kaydedilmiş bir ses düzeyine sahiptir. Ses seviyesi 3 olarak ayarlanmışsa ve haber kanallarında sesi çok anlaşılmayan bir muhabiri dinliyorsanız, muhtemelen düğmeyi çevirirsiniz veya 11'e kadar arttırmak için uzaktan kumandanıza basarsınız. Bu ses seviyesi 3'ten 11'e değiştirme eylemi, bir nesnenin durumunu (bu durumda sesi) yeni bir değere değiştirdiğimiz memento modelinin basit bir biçimidir. Kritik olarak, daha önce sahip olduğumuz herhangi bir ses seviyesi değerine geri dönerek bu değişikliği herhangi bir zamanda geri döndürebiliriz.

Daha eski televizyonlar için bu işlem artımlı olacaktır, yani 3'ten 4'e 5'e gidiyoruz, vb. Yine de, bazı yeni televizyonlarda, hemen atlamak için açıkça bir değer seçebilir ve tek bir adımda 3'ten 11'e değişiklik yapabiliriz. Her şeye rağmen, bu tekniklerin her ikisi de memento tasarım modeli uygulamalarında yaygın olarak kullanılmaktadır. Bazı durumlarda, geri alma ve yineleme, yedek konumu olarak en son durumu kullanarak yalnızca artımlı şekilde gerçekleşir. Aşağıda inceleyeceğimiz kod uygulaması gibi diğer durumlarda, önceki durumlara geri dönme herhangi bir sırayla gerçekleşebilir ve bu da revizyon geçmişinde gerektiği kadar atlayış yapmamızı sağlar.

Yani özetleyecek olursak; memento tasarımının en büyük olayı ihtiyaç duyulduğunda“geri al” işlemini anlık(snapshot) olarak gerçekleştirmesidir. Bunu tıpkı sayfalarda gezinirken birden önceki sayfaya geri dönmek için kullandığımız    butonuna benzetebiliriz.

Evet, daha fazla bilgiye boğmadan örnek üzerinden anlatmak istiyorum. Yoksa Gerçek bir uygulama üzerinden anlatılmadıkça bütün bu bilgiler havada kalacak. Şimdi gelelim ilk örneğimize.









 
<img alt="memento genel uml diyagrami" src="https://github.com/osmnbkrerk/Memento-Design-Pattern/blob/main/resimler/diyagram1.png"></html>


İlk örneğimizin UML sınıf diyagramı bu şekilde. Gayet basit bir örnekle başlamak istedim.

Article Sınıfı

public class Article{

    private long id;
    private String title;
    private String content;
     
    public Article(long id, String title) {
        super();
        this.id = id;
        this.title = title;
    }
     
    public void setContent(String content) {
    this.content = content;
    }
     
     public String getContent(){
         return content;
     }
     
    public ArticleMemento createMemento(){
        ArticleMemento m = new ArticleMemento(id, title, content);
        return m;
    }
     
    public void restore(ArticleMemento m) {
        this.id = m.getId();
        this.title = m.getTitle();
        this.content = m.getContent();
    }

 
    @Override
    public String toString() {
        return "Article [id=" + id + ", title=" + title + ", content=" + content + "]";
    }


Bu örnekte, üç temel niteliğe sahip bir Makale nesnesi için memento oluşturuyoruz. Makalemizin id’si, başlığı ve içeriği vardır. ArticleMemento sınıfı, Article nesneleri için memento olarak kullanılır. Yani Article sınıfımız en başta bahsettiğim üzere Originator sınıfının görevini üstleniyor.

Article sınıfımızda createMemento() fonksiyonun tipi ArticleMemento olup görevi durağan bir memento oluşturmaktır. 

Restore() fonksiyonu ise Originator’ün dahili durumunu geri alma/yüklemeyi sağlar. Parametreyle gelen ArticleMemento tipindeki nesnenin id’si, başlığı ve içeriği tekrar Article sınıfındaki değişkenlerine(id,title,content) set edilir. Bu sayede geri alma işlemini başarıyla gerçekleştirir.


ArticleMemento Sınıfı


public final class ArticleMemento{
    private final long id;
    private final String title;
    private final String content;
     
    public ArticleMemento(long id, String title, String content) {
        super();
        this.id = id;
        this.title = title;
        this.content = content;
    }
 
    public long getId() {
        return id;
    }
 
    public String getTitle() {
        return title;
    }
 
    public String getContent() {
        return content;
    }
}

ArticleMemento sınıfı ise Memento sınıfının görevini üstlenir. Çeşitli özellikleri barındırır ve getter/setterları bulunmaktadır. Originator sınıfımız olan Article bu sınıftan nesne create eder.

Bu sınıfı, Originator(Article) sınıfı tarafından yazılan ve okunan ve CareTaker(Main) sınıfı tarafından yönlendirilen kilitli bir kutu olarak düşünebiliriz. Prensip olarak, bir hatıranın değişmez bir nesnede olması gerekir, böylece hiç kimse yaratıldıktan sonra durumunu değiştiremez(final class olmasının sebebi).


Main Sınıfı 

Main sınıfımız, memento nesnelerini yaratan ve geri yükleyen Caretaker olarak davranır.

public class Main {
    public static void main(String[] args){

        Article article = new Article(1, "Makalem");
        article.setContent("Orijinal icerik");   //orijinal içeriğimiz
        System.out.println(article);
         
        ArticleMemento memento = article.createMemento();
  //durağan bir memento oluşturduk
         
        article.setContent("Baska icerik");      
        System.out.println(article);
         
        article.restore(memento);       	//Değişiklikleri geri alır
        System.out.println(article);    	//orijinal içeriği tekrar yazdırır
    }
}




Ekran çıktımız ise şu şekilde;


Article [id=1, title=Makalem, content=Orijinal icerik]
Article [id=1, title=Makalem, content=Baska icerik]
Article [id=1, title=Makalem, content=Orijinal icerik]







Dürüst olalım herkes oyun oynamayı sever. Bu örneğimiz oyunlardaki skorlarımızın save(kaydetme) ve load(yükleme) işlemleriyle alakalı olacak. 

İlk örneğimiz biraz basitti. Şimdi biraz daha karmaşık örneğimizi inceleyelim. 

Yüksek puan aldığımız bir oyun oynadığımızı ve bu puanı kaydetmek istediğimizi düşünün. Puan farklı olmasa bile yüksek puanı almak istiyoruz. Bu sorunu nasıl çözeceğiz? Memento tasarım deseni uygulaması için bileşenleri düşünmeye çalışalım. Peki ön koşullarımız nelerdir?


1-	Nesne durumunun kaydedilebileceği bir yol olmalı.
2-	Memento nesnesine doğrudan erişim için kod çağırmak istemiyoruz.
3-	Memento nesnesi yalnızca Originator aracılığıyla oluşturulmalıdır. Yani CareTaker kendi başına üzerinde herhangi bir işlem yapamaz.

 
<img alt="memento genel uml diyagrami" src="https://github.com/osmnbkrerk/Memento-Design-Pattern/blob/main/resimler/diyagram2.png"></html>




















MementoToUser Arayüzü

public interface MementoToUser {
    
}

Kullanıcının Memento'ya erişmesi için bir ortam olan MementoToUser arayüzünü oluşturduk. Bu arayüzde tanımlanmış herhangi bir işlem olmayacaktır.


MementoToController Arayüzü

public interface MementoToController {
    public abstract int getScore();
}

Oyun denetleyicisinin üzerinde işlemler gerçekleştirmesi için bir ortam olan MementoToController arayüzünü oluşturduk. yani puan almak istiyoruz.



Memento Sınıfı 

public class Memento implements MementoToController, MementoToUser {
 
	private int score;
 
	public Memento(int highScore) {
		this.score = highScore;
	}
 
	@Override
	public int getScore() {
		return score;
	}
}

Hem MementoToUser hem de MementoToController'ı implement eden Memento adlı bir sınıf oluşturduk. 

Sınıfın kurucu metodunda parametreden gelen yüksek skorumuzu, score adlı değişkene set ediyoruz. Bu gerekli çünkü bu nesneyi ona en yüksek puanı geçirerek oluşturacağız. MementoToController arayüzünden gelen getScore() metodunu en yüksek skora erişmek için implement ediyoruz. 




Game Arayüzü 

public interface Game {
 
	public abstract MementoToUser saveGame();
 
	public abstract void loadGame(MementoToUser memento);
 
	public abstract int getHighestScore();
	
	public abstract void setHighestScore(int score);
}


Game adında bir arayüz oluşturduk ve içerisinde durumu kaydetmek için saveGame(), durumu yüklemek için loadGame() ve en yüksek skoru get/set etmek için getHighestScore(), setHighestScore() metotları bulunmakta.


GameImpl Sınıfı

public class GameImpl implements Game {
 
	private int highScore;
 
	@Override
	public MementoToUser saveGame() {
		return new Memento(highScore);
	}
 
	@Override
	public void loadGame(MementoToUser memento) {
	this.highScore = ((MementoToController) memento).getScore();
	}
 
	@Override
	public int getHighestScore() {
		return highScore;
	}
 
	@Override
	public void setHighestScore(int score) {
		this.highScore = score;
	}
}


Sınıfımızın isminden de anlaşılacağı üzere Game arayüzüne implement eden bir sınıf. Game arayüzünden gelen metotları implement ettikten sonra çeşitli işlemleri gerçekleştirmesi için Override ettik. 

Bir önceki örnekte de anlattığım üzere saveGame(), loadGame() fonksiyonları bu uygulamanın kilit metotlarıdır. Yani tüm olay bu 2 metotta bitiyor. Bununla ilgili örneği Test Class’ımız  olan Client adlı sınıfta vereceğim. 


Client Sınıfı

public class Client {
    
	public static void main(String[] args) {
 
		Game game = new GameImpl(); 
		game.setHighestScore(200);  
		System.out.println(game.getHighestScore());
		MementoToUser memento = game.saveGame();
		game.setHighestScore(100); 
		System.out.println(game.getHighestScore());
		game.loadGame(memento);
		System.out.println(game.getHighestScore());
	}
}

Client sınıfımızda GameImpl() nesnesi oluşturuyoruz. game referans değişkenimizin en yüksek skorunu 200 olarak set ediyoruz. Ekrana en yüksek skorumuzu yazdırdıktan sonra memento oluşturuyoruz. Burada bir nevi oyunu kaydediyoruz. 
Şöyle düşünelim oyunda belirli bir seviyeye geldik. Biz bu oyunumuzun arka planda kaydedilmesini isteriz. Çünkü tekrar oynadığımızda daha kötü oynayıp daha düşük skor alma ihtimalimiz var.  

game referansımızın altındaki setHighestScore metoduna 100’ü parametresinde yolladık ve artık en yüksek skorumuz 100. 

Fakat 1 dakika…

Bizim önceki skorumuz 100’den daha yüksekti o yüzden game’in altındaki loadGame() metodunu kaydettiğimiz mementoyu parametre olarak yollayarak çağırıyoruz. Ve tekrar en yüksek skoru yazdırıyoruz.


Ekran Çıktımız şu şekilde;

200
100
200
