# EthereumTutorial
Metamask + Faucet + remix <br />

Instalar o metamask no Chrome (https://metamask.io/)  <br />
configurar o metamask (salvar a frase) <br />
pegar Ether via faucet (https://faucet.metamask.io/) <br />
ir no remix  usar o hello.sol <br />
Setar para usar o metamask e fazer o upload do contrato <br />
Criar conta e configurar o infura (https://infura.io/) <br />



Utilizando Web3j para trabalhar com smart contracts no Java <br />

https://docs.web3j.io/getting_started.html <br />
https://github.com/web3j/web3j/releases <br />

Baixar a ferramenta, criar um projeto (no eclipse ou netbeans) e importar todas bibliotecas <br />
 Caso não tenha feito a compilação via solc, criar 2 arquivos um .bin e um .abi <br />
copiar o código binário do web3deploy para o arquivo .bin e a abi para o arquivo .abi <br />
./web3j solidity generate ~/caminho/Contrato.bin ~/caminho/Contrato.abi -o ~/Caminho/ -p teste <br />
Importar a classe Java que foi criada no seu projeto, adicionalmente verificar se ela está correta <br />
Para iniciar a conexão no seu programa, usar o Conexao.java <br />
Para fazer o delploy de um contrato via web3j usar o Interação.java <br />




Outras informações <br />
Pode ser usadas Blockchains publicas especiais para teste e desenvolvimento (testnet, robstein...) onde não é preciso fazer a mineração. <br />

Caso não tenha interesse em manter uma infraestrutura (um servidor com Geth funcionando para suas aplicações) pode ser utilizado o Infura que disponibiliza acesso a blockchain pública (inclusive as de teste) <br />

Existem outros programas cliente que podem ser utilizados além do Geth como o eth, pyethapp e ganashi(voltado para desenvolvimento ) <br />

Truffle é uma suite de aplicações para ajudar no desenvolvimento <br />

Existem bibliotecas para serem utilizadas com outras linguagens de programação (como web3js para javascript, web3py para python…)  <br />
