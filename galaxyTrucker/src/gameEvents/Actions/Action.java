package gameEvents.Actions;

public abstract class Action {
    private final String actionName;

    public Action(String actionName){
        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }

    
}
