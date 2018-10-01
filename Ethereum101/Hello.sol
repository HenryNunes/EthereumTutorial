pragma solidity ^0.4.0;

contract Hello
{
    string private valor = "hello world";
    
    function getValor() public view returns(string)
    {
        return valor;
    }
    function setValor(string a) public returns(bool)
    {
        valor = a;
        return true;
    }
}

