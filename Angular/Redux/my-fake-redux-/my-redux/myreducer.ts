import { ActionType } from './ActionType';
export interface Reducer<T> {

    ( state : T , action : ActionType) : T ;

}