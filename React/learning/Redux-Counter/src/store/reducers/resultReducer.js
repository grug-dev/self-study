import * as actionType  from '../actions/actionTypes';

const initialState = {
    results: []
}



const resultsReducer = (state = initialState , action) => {   
    switch (action.type) {                
        case actionType.STORE_RESULT:
            const newResults = [...state.results , action.payLoad.currentCounter];              
            return {
                ...state,
                results: newResults
            }    
        case actionType.DELETE_RESULT:            
            const myArray =  [...state.results];
            const index = action.payLoad.index;
            myArray.splice(index, 1);
            return {
                ...state ,
                results: myArray
            };
        default:
            break;
    }    
    return state;
}


export default resultsReducer;