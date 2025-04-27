package gameEvents.Actions;

//TODO rivedere l'implementazione e togliere il parametro action name
public abstract class Action {
    private final String actionName;

    public Action(String actionName){
        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }

    
}
