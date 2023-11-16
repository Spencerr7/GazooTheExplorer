public class Space {
    LivingThing occupant;
    Treasure cache;

    public Space(){
        this.occupant = null;
        this.cache = null;
    }

    public Space(Treasure treasure){
        this.cache = treasure;
    }

    public Space(LivingThing occupant){
        this.occupant = occupant;
    }

    public void setOccupant(LivingThing occupant){
        this.occupant = occupant;
    }

    public LivingThing getOccupant(){
        return occupant;
    }

    public String getConsoleStr(){
        if(occupant == null){
            return "- ";
        }else{
           return occupant.getConsoleStr() + " ";
        }
    }

    public Treasure getCache() {
        return cache;
    }

    public void setCache(Treasure cache) {
        this.cache = cache;
    }

    public String getConsoleStr(boolean reveal){
        if(reveal == true){
            if(this.cache !=null){
                return this.cache.getConsoleStr() + " ";
            }else if(this.occupant !=null){
                return this.occupant.getConsoleStr() + " ";
            }else{
                return "- ";
            }
        }else{
            if(this.occupant instanceof Explorer){
                return this.occupant.getConsoleStr() + " ";
            }else{
                return "- ";
            }
        }
    }
}
