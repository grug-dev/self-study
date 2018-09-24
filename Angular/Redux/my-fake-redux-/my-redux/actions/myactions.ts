import { ActionFactory } from "../ActionFactory";
import { ActionType } from "../ActionType";
import { State } from "../State";

let oldState : State = {
    value : 10 
}

export let incrementActionType : ActionType = {
    type : ActionFactory.INCREMENT_ACTION
}

export let decrementActionType : ActionType = {
    type : ActionFactory.DECREMENTAR_ACTION 
}

export let multiplyActionType : ActionType = {
    type : ActionFactory.MULTIPLICAR_ACTION, 
    payload: 2
}

export let resetAction : ActionType = {
    type : ActionFactory.RESET_ACTION, 
    payload: 2
}