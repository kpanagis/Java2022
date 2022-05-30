public class Material extends Entity 
{
    private double levelA; 
    private double levelB; 
    private double levelC;
    //xaraktiristika material
    public Material (int id, String name, double lA, double lB, double lC){
        setID(id);
        setName(name);
        setIsService(false);
        levelA = lA;
        levelB = lB;
        levelC = lC;
    }
     //xorizoume se levels ton arithmo twn atwmwn pou dikaioudai dwra  me taktiki override thn
     //levelA apoteleitai apo 1 atomo
      @Override
    public double getLevelA(){
        return levelA;
    }
    //levelB apoteleitai apo 2-4 atoma
    @Override
     public double getLevelB(){
        return levelB;
    }


    //levelC apoteleitai apo 5 perissotera atoma
    @Override
     public double getLevelC(){
        return levelC;
    }

    //apo abstract method ths Entity class
    public String getDetails(){
        return "This is a Material." + "For one member families  , the quantity is: " + this.levelA + ", for 2-4 members families, the quantity is: "
        + this.levelA + " , for  members families , the quantity is: " + this.levelC;
    }
    
  
}