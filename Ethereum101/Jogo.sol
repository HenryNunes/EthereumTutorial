pragma solidity ^0.4.7;

contract Jogo{
    
    bool private ativo;
    address private vencedor;
    int private apostadores;
    Apostador[] private  vetor;
    
    struct Apostador{
        address end;
        uint aposta;
    }
    
    constructor() public
    {
        ativo = true;
        apostadores = 0;
    }
    
    function apostar()public payable
    {
        require(ativo == true);
        apostadores++;
        Apostador memory temp = Apostador(msg.sender, msg.value);
        vetor.push(temp);
        if(apostadores >= 3) finalizar();
    }
    function finalizar()private
    {
        ativo = false;
        if((vetor[1].aposta < vetor[0].aposta && vetor[2].aposta > vetor[0].aposta) || 
        (vetor[2].aposta < vetor[0].aposta && vetor[1].aposta > vetor[0].aposta)) vetor[0].end.transfer(address(this).balance);
        
        else if((vetor[0].aposta < vetor[1].aposta && vetor[2].aposta > vetor[1].aposta) || 
        (vetor[2].aposta < vetor[1].aposta && vetor[0].aposta > vetor[1].aposta)) vetor[1].end.transfer(address(this).balance);
       
        else  vetor[2].end.transfer(address(this).balance);
    }
    function getEstado() public view returns(bool)
    {
        return ativo;
    }
}
