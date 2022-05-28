package AcoPackage;

public class Mapa {
	
	private float[][]location;
	private float [][]feromone;
	private float [][]matrixH;
	
	private int start;
	private int finish;
			
	Mapa(){
		this.location=new float[7][7];
		this.feromone=new float[7][7];
		this.matrixH=new float[7][7];
		this.start=0;
		this.finish=6;
		
		iniciaizar_mapa();
		inicializar_matrizH();
		inicializar_feromona();
	
		
	}
	private void inicializar_feromona() {
		for(int i=0;i<7;i++) {
			for (int x=0;x<7;x++) {
				feromone[i][x]=1;
			}
		}
	}

	private void inicializar_matrizH() {
		//agregar la matrizz H
		for (int i=0;i<7;i++) {
			for (int x=0;x<7;x++) {
				if(location[i][x]>0) {
					matrixH[i][x]=1/location[i][x];
				}
			}
		}
		
	}
	private void iniciaizar_mapa() {
		location[0][1]=5;
		location[0][2]=5;
		location[1][0]=5;
		location[1][2]=1;
		location[1][4]=6;
		location[1][6]=3;
		location[2][0]=5;
		location[2][1]=1;
		location[2][3]=2;
		location[3][2]=2;
		location[3][6]=3;
		location[4][5]=2;
		location[4][1]=6;
		location[4][6]=1;
		location[5][4]=2;
		//location[5][6]=3;
		location[6][1]=3;
		location[6][3]=3;
		location[6][4]=1;
		//location[6][5]=3;
	}
	
	public float[][] get_ubicacion() {
		return location;
		
	}
	public float[][] get_feromona(){
		return feromone;
	}
	public float[][] get_matrizH(){
		return matrixH;
	}
	public void imprimir_ubicaciones() {
		for (int i=0;i<7;i++) {
			for(int x=0;x<7;x++) {
				System.out.print(location[i][x]+"  ");
			}
			System.out.print("\n");
		
		}
	}
	public void imprimir_matrizH() {
		
		for (int i=0;i<7;i++) {
			for(int x=0;x<7;x++) {
				System.out.print(matrixH[i][x]+"  ");
			}
			System.out.print("\n");
		
		}
	}
public void imprimir_feromona() {
		
		for (int i=0;i<7;i++) {
			for(int x=0;x<7;x++) {
				System.out.print(feromone[i][x]+"  ");
			}
			System.out.print("\n");
		}
	}

public void evaporizar_feromona() {
	
	for (int i=0;i<7;i++) {
		for(int x=0;x<7;x++) {
			System.out.print("  ");
			feromone[i][x]=(float)(1-0.5)*feromone[i][x];
		}
		
	}
}
	public int get_inicio() {
		return start;
	}
	public int get_final() {
		return finish;
	}
	public void mejor_camino() {
		float best=0;
		int start=0;
		
			for(int i=0;i<7;i++) {
				best=feromone[0][i];
				if(feromone[0][i]>best) {
					best=feromone[0][i];
				}
			}
	}
}
