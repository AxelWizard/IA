package PSOPackage;
import java.text.DecimalFormat;
import java.text.ParseException;

public class PSO {
    private double w;
    private double c1;
    private double c2;
	PSO(){
		this.w=0.8;
		this.c1=2;
		this.c2=2;
	}
	
	public void EvaluateFitness(Population p,DataSet d) { //Fitness of each particle
		double[] particlesFitness = p.get_fitness();
		
		int size=15;
        double aux = 0.0, sum = 0.0;
        for(int i = 0; i < size; i++){        
            sum = d.GetSum(p.get_particulas()[i]);
            aux = (1.0 / 9.0) * sum;
            particlesFitness[i] = 100 - Math.abs(aux - d.Benetton());
            particlesFitness[i]=reducion(particlesFitness[i]);
        }
    }
    public void EvaluatePBest(Population p) {
    	double[][] particles = p.get_particulas();
    	double[] fitness = p.get_fitness();
    	double[][] posPBest= p.get_posPBest();
    	double[] fitnessPBest=p.get_fitnessPBest();
        for (int i = 0; i < 15; i++) {
        		

            if (Math.abs(fitness[i]) < Math.abs(fitnessPBest[i])) {
                posPBest[i][0] = particles[i][0]; 
                posPBest[i][1] = particles[i][1];
                posPBest[i][2] = particles[i][2];
                fitnessPBest[i] = fitness[i]; 
                
            }
        }
    }
    public void EvaluateGBest(Population p) {
    	double[][] particles = p.get_particulas();
    	double[] posGBest = p.get_posGBest();
    	double[] fitnes=p.get_fitness();
    	for(int x=0;x<15;x++) {
    		if( Math.abs(fitnes[x]) <  Math.abs(p.get_global())) {
    		
    			posGBest[0]=particles[x][0];
    			posGBest[1]=particles[x][1];
    			posGBest[2]=particles[x][2];
    		
    			p.set_global(fitnes[x]);
    		
    		}
    	}
    }
    
    public void calVelocity(Population p) {
    	double[][] particles = p.get_particulas();
    	double[][] velocity = p.get_velocity();
    	double[][] posPBest = p.get_posPBest();
    	double[] posGBest = p.get_posGBest();
    	double r1,r2;
    	for(int i=0; i<15;i++) {
    		r1=aleatorio();
    		r2=aleatorio();
	        velocity[i][0] = w * velocity[i][0] + (c1 * r1 * (posPBest[i][0] - particles[i][0])) + (c2 * r2 * (posGBest[0] - particles[i][0])); //x
	        velocity[i][1] = w * velocity[i][1] + (c1 * r1 * (posPBest[i][1] - particles[i][1])) + (c2 * r2 * (posGBest[1] - particles[i][1])); //y
	        velocity[i][2] = w * velocity[i][2] + (c1 * r1 * (posPBest[i][2] - particles[i][2])) + (c2 * r2 * (posGBest[2] - particles[i][2])); //z

    	}
    }
    private double aleatorio() {
    	double x=(double)Math.random();
		String cad=String.valueOf(x);
		cad = cad.substring(0,3);
		x = Double.parseDouble(cad);

		return x;
    }
    public void UpdatePosition(Population p) {
    	double[][] particles = p.get_particulas();
    	double[][] velocity = p.get_velocity();
    	for (int i=0;i<15;i++) {
    	
    		particles[i][0] += velocity[i][0]; //x
    		particles[i][1] += velocity[i][1]; //y
    		particles[i][2] += velocity[i][2]; //z
    	}
    }
    public double reducion(double x) {
    	
    	String cad=String.valueOf(x);
    	String aux="";
    	int cont=0;
    	for(int i=0;i<cad.length();i++) {
    		if(cad.charAt(i)!='.') {
    			cont++;
    			aux+=cad.charAt(i);
    		}
    		else {
    			aux+=cad.charAt(i);
    			aux+=cad.charAt(i+1);
    			if(i+2<cad.length())
    				aux+=cad.charAt(i+2);
    			if(i+3<cad.length())
    				aux+=cad.charAt(i+3);
    			i=cad.length();
    			}
    	}
    	x = Double.parseDouble(aux);
    	
		return x;
    }
}
