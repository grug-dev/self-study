
import * as actionTypes from './actionTypes';


const saveResult = (currentCounter) => {
    return {
        type: actionTypes.STORE_RESULT  ,
        payLoad: {
            currentCounter: currentCounter
        }     
    };
};

export const storeResult = (currentCounter) => {
    return ( dispatch,  getState) => {
        setTimeout( () => {
            const oldCounter = getState().ctr.counterRedux;
            console.log("OldCounterBeforeStoreResult" , oldCounter);
            dispatch(saveResult(currentCounter));
        }, 2000);
    }
};

const deleteResultActionCreator = (id) => {
    return {
        type: actionTypes.DELETE_RESULT  ,
        payLoad: {
            index : id
        }     
    };
};


export const deleteResult = (id) => {
    return (dispatch) => {
        setTimeout( () => {
            console.log("Deleting..." , id);
            dispatch(deleteResultActionCreator(id));
            console.log("Deleted...");
        }, 1000);
    }
};