package aed;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


public class ColaPrioridadtests {
    
    @Test
    void probarsifuncionaqsy(){
        ArrayList list = new ArrayList<Integer>(Arrays.asList(new Integer[]{3,5,4,1,2}));
        System.out.println(list.toString());
        ColaPrioridad test = new ColaPrioridad<Integer>(list,Comparator.naturalOrder()); 
        System.out.println(test.toString());
        System.out.println(test.sacarMaximo());
        System.out.println(test.toString());
        test.agregar(100);
        test.agregar(101);
        test.agregar(50);
        test.agregar(50);
        System.out.println(test.toString());
    }
}
