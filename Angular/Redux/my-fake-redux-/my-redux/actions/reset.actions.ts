import { Action } from "../actions/Action";
import { State } from "../State";
import { ActionType } from "../ActionType";

export class ResetAction implements Action {

    payLoad : ActionType["payload"] ;

    constructor ( payLoad : ActionType["payload"] ) {
        this.payLoad = payLoad ;
    }


    send( oldState: State): State {
        let oldValue:number = oldState.value ;

        let newState : State = {
            value : 0
        }

        return newState;

    }
    
}