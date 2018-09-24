import React, {Component} from "react";

const TableHeader = () => {
    return (
        <thead>
            <tr>
                <th>Name</th>
                <th>Job</th>
            </tr>
        </thead>
    );
}

const TableBody = (props) => {

    const rows = props
        .data
        .map((row, index) => {
            return (
                <tr key ={index}>
                    <td>{row.job}
                        </td>
                    <td>{row.name}</td>
                    <td>
                        <button name="" id="" className="btn btn-danger" value="{index}" 
                        onClick = {
                            () =>  props.removeCharacter(index)                            
                        }>
                        Eliminar                            
                        </button>
                    </td>
                </tr>
            );
        });

    return (<tbody>
        {rows}
    </tbody>);
}

class Table extends Component {

    render() {

        const {data , removeCharacter} = this.props; // ES6

        return (
            <table>
                <TableHeader/>
                <TableBody 
                    data={data} removeCharacter={removeCharacter}
                />
            </table>
        );
    }
}
export default Table;