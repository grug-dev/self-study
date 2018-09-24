import { Action } from "./actions/Action";
import { ActionType } from "./ActionType";
import { DecreaserAction } from "./actions/decreaser.actions";
import { IncrementerAction } from "./actions/incrementer.actions";
import { MultiplyAction } from "./actions/multiply.actions";
import { ResetAction } from "./actions/reset.actions";

export class ActionFactory {
    action: ActionType;
    
    public static INCREMENT_ACTION : string = 'INCREMENTAR';
    public static  DECREMENTAR_ACTION : string = 'DECREMENTAR'; 
    public static  MULTIPLICAR_ACTION : string = 'MULTIPLICAR'; 
    public static  RESET_ACTION : string = 'RESET'; 

    constructor ( action:ActionType) {
        this.action = action;
    }

    getAction():Action {
        switch ( this.action.type) {
            case ActionFactory.INCREMENT_ACTION:
                return new IncrementerAction(this.action.payload);
            case ActionFactory.DECREMENTAR_ACTION:
                return new DecreaserAction(this.action.payload);
            case ActionFactory.MULTIPLICAR_ACTION:
                return new MultiplyAction(this.action.payload);
            case ActionFactory.RESET_ACTION:
                return new ResetAction(this.action.payload);
            default:
                return null;
        }
    }
    
}