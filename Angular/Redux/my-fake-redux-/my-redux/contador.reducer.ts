import { ActionType } from './ActionType';
import { ActionFactory } from './ActionFactory';
import { State } from './State';



export function contadorReducer ( state : State = { value : 10} , actionType:ActionType ) {

    let actionFactory = new ActionFactory(actionType);
    let newState = actionFactory.getAction().send ( state) ;

    return newState ; 
}