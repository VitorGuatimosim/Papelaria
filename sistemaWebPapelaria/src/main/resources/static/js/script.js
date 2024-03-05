$(document).ready(function(){
    function carregarProdutos(){
        $.get('http://localhost:8080/produto/listar', function(data){
                $('#tabelaProdutos tbody').empty();
                for(let i = 0; i < data.length; i++){
                    let produto = data[i];
                    let id = $('<td>').text(produto.id);
                    let nome = $('<td>').text(produto.nome);
                    let valor = $('<td>').text(produto.valor);
                    let quantidade = $('<td>').text(produto.quantidade);
                    let categoria = $('<td>').text(produto.categoria);
                    let botaoAtualizar = $('<i>')
                            .addClass('bi bi-pencil')                            
                            .click(function(){
                                window.location.href = 'editarProduto/id='+produto.id;
                            });;
                    let botaoDeletar = $('<i>')
                            .addClass('bi bi-x-lg')
                            .click(function(){
                                window.location.href = 'deletarProduto/id='+produto.id;
                            });
                    let aBotaoAtualizar = $('<a href="#">').append(botaoAtualizar);
                    let aBotaoDeletar = $('<a href="#">').append(botaoDeletar);
                    
                    let tdBotaoAtualizar = $('<td>').append(aBotaoAtualizar);
                    let tdBotaoDeletar = $('<td>').append(aBotaoDeletar);

                    let tr = $('<tr>')
                            .attr('data-id', produto.id)
                            .append(id)
                            .append(nome)
                            .append(valor)
                            .append(quantidade)
                            .append(categoria)
                            .append(tdBotaoAtualizar)
                            .append(tdBotaoDeletar);
                    $('#tabelaProdutos tbody').append(tr);
                }
        });
    }
    
    function criarProduto(produto){
        $.ajax({
            url: 'http://localhost:8080/produto/adicionar',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(produto),
            error: function(){
                alert('Não foi possível criar o filme na API.');
            },            
            complete: function(){                   
                alert('Produto Cadastrado com sucesso!');
                window.location="http://localhost:8080/controleProduto";
            }            
        });
    }
    
    $('#formCriarProduto').submit(function(event){
        event.preventDefault();
        let nome = $('#txt_nome').val();
        
        if(!nome){
            alert('Por favor, preencha o nome do produto');
            return;
        }else{
            let valor = $('#txt_valor').val();
            if(!valor){
                alert('Por favor, preencha a valor do produto');
                return;
            }else{
                let quantidade = $('#txt_quantidade').val();
                if(!quantidade){
                    alert('Por favor, preencha a quantidade do produto');
                    return;
                }else{
                    let categoria = $('#txt_categoria').val();
                    if(!categoria){
                        alert('Por favor, preencha o ano de lançamento do produto');
                        return;
                    }else{
                        let produto = {
                            id: '',
                            nome: nome,
                            valor: valor,
                            quantidade: quantidade,
                            categoria: categoria
                        };
                        criarProduto(produto);
                    }
                }
           
            }
        }   
    });
    
    $('#formEditarProduto').submit(function(event){
        event.preventDefault();
        let urlId = queryString();
        let nome = $('#txt_nome').val();
        
        if(!nome){
            alert('Por favor, preencha o nome do produto');
            return;
        }else{
            let valor = $('#txt_valor').val();
            if(!valor){
                alert('Por favor, preencha a valor do produto');
                return;
            }else{
                let quantidade = $('#txt_quantidade').val();
                if(!quantidade){
                    alert('Por favor, preencha a quantidade do produto');
                    return;
                }else{
                    let categoria = $('#txt_categoria').val();
                    if(!categoria){
                        alert('Por favor, preencha o ano de lançamento do produto');
                        return;
                    }else{
                        let produto = {
                            id: '',
                            nome: nome,
                            valor: valor,
                            quantidade: quantidade,
                            categoria: categoria
                        };
                        atualizarProduto(urlId, produto);
                    }
                }
           
            }
        }   
    });
    
    function atualizarProduto(id, produto){
        $.ajax({           
           url: 'http://localhost:8080/produto/atualizar/'+id,
           method: 'PUT',
           contentType: 'application/json',
           data: JSON.stringify({
                   id: id,
                   nome: produto.nome,
                   valor: produto.valor,
                   quantidade: produto.quantidade,
                   categoria: produto.categoria
            }),
            error: function(){
                alert('deu erro');
            },
            complete: function(){
                alert('Produto Atualizado com sucesso!');
                window.location="http://localhost:8080/controleProduto";
            }
        });        
    }
    
    function queryString() {  
              var loc = window.location.href;
              let urlSplit = loc.split('=');
              return urlSplit[1];             
    }
    
    carregarProdutos();    
});

