import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Igra {

    private List<Integer> tabla ;

    public Igra(){
         tabla = new ArrayList<>(Collections.nCopies(9, 0));
    }
    public int pobeda(){
        for(int i=0;i<=6;i=i+3){
            if(tabla.get(i) !=0 && tabla.get(i).intValue()==tabla.get(i+1).intValue()&& tabla.get(i).intValue()==tabla.get(i+2).intValue())

                return tabla.get(i);
        }
        for(int i=0;i<3;i++){


            if(tabla.get(i) !=0 && tabla.get(i).intValue()==tabla.get(i+3).intValue()  && tabla.get(i).intValue()==tabla.get(i+6).intValue())

                return tabla.get(i);
        }
        if(tabla.get(0)!=0 && tabla.get(0).intValue()==tabla.get(4).intValue() && tabla.get(0).intValue()==tabla.get(8).intValue()){
            return tabla.get(0);


        }
        if(tabla.get(2)!=0 && tabla.get(2).intValue()==tabla.get(4) && tabla.get(2).intValue()==tabla.get(6).intValue()){
            return tabla.get(0);
        }
        return 0;
    }
    public void upisiPotez(int index) throws Exception {
            if(tabla.get(index)==1) throw  new Exception("vec unet index u upisi potez");
            tabla.set(index,1);
    }


}

