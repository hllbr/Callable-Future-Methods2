
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    
    public static void main(String[] args) {
        
     
        ExecutorService exeser = Executors.newFixedThreadPool(1);
        //burada future interface cinsinden bir referans vermemiz gerekiyor.Future Generic yazılmış bir interface
        Future<?> fut = exeser.submit(new Callable<Integer>() {//referansı callable için eşitlemeye çalışıyorsam türden bağımsız bir tanımlama yapabilirim ? yardımıyla
            @Override
            public Integer call() throws Exception {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               /*
               run metodu exception fırlatma riskine sahip değilken burad exception riski bulunuyor.Bunun belirtilmesi gerekiyor.
               */
               Random random = new Random();
                System.out.println("Thread Çalışıyor...");
                int sure = random.nextInt(3550)+590;
                if(sure>4130){
                    throw new IOException("Thread Çok Uzun Süre Uyudu");
                }
                try{
                Thread.sleep(sure);

                }catch(InterruptedException ex){
                     Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    
                }
                  System.out.println("Thread Çıkıyor...");
                /*
                  Threadimizin çalışması bittikten sonra bir değer döndürmek istiyorum.Bu değeri döndürmek için = 
                  */
                return sure;//Threadin kaç saniye uyuduğunu öğrenmek için bu şekilde kurdum
            }
        });
        
        
        exeser.shutdown();
        try {
            System.out.println("Dönen Değerimiz : "+fut.get());
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
          // ex.printStackTrace();
            System.out.println(ex);
        }
    }
}
