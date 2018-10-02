import * as actionTypes from './actionTypes';

// Actions Creation
export const increment = () => {
    return {
        type: actionTypes.INCREMENT  ,
        payLoad: {
            value : 1
        }     
    };
};

export const decrement = () => {
    return {
        type: actionTypes.DECREMENT  ,
        payLoad: {
            value : -1
        }     
    };
};

export const substract = () => {
    return {
        type: actionTypes.SUBSTRACT  ,
        payLoad: {
            value : -5
        }     
    };
};

export const add = () => {
    return {
        type: actionTypes.ADD  ,
        payLoad: {
            value : 5
        }     
    };
};