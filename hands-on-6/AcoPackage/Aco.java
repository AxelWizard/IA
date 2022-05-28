package AcoPackage;
import java.util.ArrayList;
import java.util.List;

public class Aco {
	
	
	List<Integer> _list = new  ArrayList<Integer> ();
	List<Integer> position = new  ArrayList<Integer> ();
	List<Float> weight = new  ArrayList<Float> ();
	List<Float>  proba=new  ArrayList<Float> ();

	Aco()
	{
		
	}
	public void inicio(Mapa m) {
		_list.add(m.get_inicio());
		
	}
	public void camino(Mapa m) {
		float[][] matrixH = m.get_matrizH();
		float[][] feromone = m.get_feromona();
		
		int counter=_list.size()-1;
		//int cont=0;
		float aux2=0;
		
		 do{
			 
			int size=_list.size();
			size=_list.get(size-1);
			for (int i=0;i<7;i++) {
				if(matrixH[size][i]!=0 ) {
					if(!_list.contains(i)) {
						position.add(i);
						aux2= feromone[size][i] * (matrixH[size][i]*matrixH[size][i]);
						weight.add(aux2);
						
					}
				}	
				
			}
			if(position.size()==0) {
				int aux=0;
				aux=_list.size();
				aux=_list.get(aux-2);
				
				_list.add(aux);
			}else {
				float sum= 0;
				for(int i=0;i<weight.size();i++) {
					
					sum+=weight.get(i);

					
				}
				
				for(int i=0;i<weight.size();i++) {
					float result;
					result=weight.get(i)/sum;
					proba.add(result);
					
				}
				float choice=0;
				float rand=probailidad();
				for(int i=0;i<weight.size();i++) {
					choice+=proba.get(i);
					
					if(rand<choice) {
						float a=position.get(i);
						_list.add((int)a);	
						i=7;
					}
					
				}
			}
			position.clear();
			proba.clear();
			weight.clear();
			
			counter=_list.size()-1;
			
		}while(_list.get(counter)!= m.get_final());
		
		System.out.println("Recorrido final "+_list);
		sacar_distancia(m);
		_list.clear();
	}
	public void sacar_distancia(Mapa m) {
		float[][] _location = m.get_ubicacion();
		float[][] feromone=m.get_feromona();
		int size=_list.size();
		int aux=0;
		int aux2=0;
		float counter=0;
		float fero=0;
		for(int i=0;i<size-1;i++) {
			aux=_list.get(i);
			aux2=_list.get(i+1);
			counter=counter + _location[aux][aux2];		
			}
		System.out.println("Imprimir el counter  "+counter );
		fero=1/counter;
		for(int i=0;i<size-1;i++) {
			aux=_list.get(i);
			aux2=_list.get(i+1);
			 feromone[aux][aux2]=feromone[aux][aux2]+fero;		
			}
	}
	public float probailidad() {
		float value=(float) Math.random();
		return value;
	}
}
