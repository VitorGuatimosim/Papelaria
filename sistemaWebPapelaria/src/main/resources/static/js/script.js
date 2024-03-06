$(document).ready(function(){
    
    /*  SCRIPTS DE PRODUTO */
    
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
                alert('Não foi possível criar o produto na API.');
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
    
    /*  SCRIPTS USUÁRIO */
    
    function carregarUsuarios(){
        $.get('http://localhost:8080/usuario/listar', function(data){
                $('#tabelaUsuarios tbody').empty();
                for(let i = 0; i < data.length; i++){
                    let usuario = data[i];
                    let id = $('<td>').text(usuario.id);
                    let nome = $('<td>').text(usuario.nome);
                    let cpf = $('<td>').text(usuario.cpf);
                    let data_nascimento = $('<td>').text(usuario.data_nascimento);
                    let data_admissao = $('<td>').text(usuario.data_admissao);
                    let celular = $('<td>').text(usuario.celular);
                    let salario = $('<td>').text(usuario.salario);
                    let cargo = $('<td>').text(usuario.cargo);
                    let usuario_nome = $('<td>').text(usuario.usuario_nome);
                    let senha = $('<td>').text(usuario.senha);
                    let botaoAtualizar = $('<i>')
                            .addClass('bi bi-pencil')                            
                            .click(function(){
                                window.location.href = 'editarUsuario/id='+usuario.id;
                            });;
                    let botaoDeletar = $('<i>')
                            .addClass('bi bi-x-lg')
                            .click(function(){
                                window.location.href = 'deletarUsuario/id='+usuario.id;
                            });
                    let aBotaoAtualizar = $('<a href="#">').append(botaoAtualizar);
                    let aBotaoDeletar = $('<a href="#">').append(botaoDeletar);
                    
                    let tdBotaoAtualizar = $('<td>').append(aBotaoAtualizar);
                    let tdBotaoDeletar = $('<td>').append(aBotaoDeletar);

                    let tr = $('<tr>')
                            .attr('data-id', usuario.id)
                            .append(id)
                            .append(nome)
                            .append(cpf)
                            .append(data_nascimento)
                            .append(data_admissao)
                            .append(celular)
                            .append(salario)
                            .append(cargo)
                            .append(usuario_nome)
                            .append(senha)
                            .append(tdBotaoAtualizar)
                            .append(tdBotaoDeletar);
                    $('#tabelaUsuarios tbody').append(tr);
                }
        });
    }
    
    function criarUsuario(usuario){
        $.ajax({
            url: 'http://localhost:8080/usuario/adicionar',
            method: 'POST',
            contentType: "application/json",
            data: JSON.stringify(usuario),
            error: function(data){
                alert("Não foi possível cadastrar o usuário no API!");
                console.log(data);
            },            
            complete: function(){                   
                alert('Usuário Cadastrado com sucesso!');
                window.location="http://localhost:8080/controleUsuario";
            }            
        });
    }
    
    $('#formCriarUsuario').submit(function(event){
        event.preventDefault();
        
        let nome = $('#txt_nome_usuario').val();
        if(!nome){
            alert('Por favor, preencha o nome do usuário');
            return;
        }else{
            let data_nascimento = $('#txt_data_nascimento').val();
            
            if(!data_nascimento){
            alert('Por favor, preencha a data de nascimento do usuário');
            return;
            }else{                
                let celular = $('#txt_celular').val();
                
                if(!celular){
                alert('Por favor, preencha o número de celular do usuário');
                return;
                }else{                    
                    let cargo = $('#txt_cargo').val();
                    
                    if(!cargo){
                    alert('Por favor, preencha o cargo do usuário');
                    return;
                    }else{                        
                        let senha = $('#txt_senha').val();
                        
                        if(!senha){
                        alert('Por favor, preencha a senha do usuário');
                        return;
                        }else{
                            let cpf = $('#txt_cpf').val();
                            
                            if(!cpf){
                            alert('Por favor, preencha o CPF do usuário');
                            return;
                            }else{
                                let data_admissao = $('#txt_data_admissao').val();
                                
                                if(!data_admissao){
                                alert('Por favor, preencha a data de admissão do usuário');
                                return;
                                }else{
                                    let salario = $('#txt_salario').val();
                                    
                                    if(!salario){
                                    alert('Por favor, preencha o salario do usuário');
                                    return;
                                    }else{
                                        let usuario_nome = $('#txt_usuario').val();
                                        
                                        if(!usuario_nome){
                                        alert('Por favor, preencha o usuário de acesso do usuário');
                                        return;
                                        }else{
                                            let usuario = {
                                                id: '',
                                                nome: nome,
                                                data_nascimento: data_nascimento,
                                                celular: celular,
                                                cargo: cargo,
                                                senha: senha,
                                                cpf: cpf,
                                                data_admissao: data_admissao,
                                                salario: salario,
                                                usuario_nome: usuario_nome
                                            };
                                            criarUsuario(usuario);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }         
    });
    
    $('#formEditarUsuario').submit(function(event){
        event.preventDefault();
        let urlId = queryString();
        let nome = $('#txt_nome_usuario').val();
        if(!nome){
            alert('Por favor, preencha o nome do usuário');
            return;
        }else{
            let data_nascimento = $('#txt_data_nascimento').val();
            
            if(!data_nascimento){
            alert('Por favor, preencha a data de nascimento do usuário');
            return;
            }else{                
                let celular = $('#txt_celular').val();
                
                if(!celular){
                alert('Por favor, preencha o número de celular do usuário');
                return;
                }else{                    
                    let cargo = $('#txt_cargo').val();
                    
                    if(!cargo){
                    alert('Por favor, preencha o cargo do usuário');
                    return;
                    }else{                        
                        let senha = $('#txt_senha').val();
                        
                        if(!senha){
                        alert('Por favor, preencha a senha do usuário');
                        return;
                        }else{
                            let cpf = $('#txt_cpf').val();
                            
                            if(!cpf){
                            alert('Por favor, preencha o CPF do usuário');
                            return;
                            }else{
                                let data_admissao = $('#txt_data_admissao').val();
                                
                                if(!data_admissao){
                                alert('Por favor, preencha a data de admissão do usuário');
                                return;
                                }else{
                                    let salario = $('#txt_salario').val();
                                    
                                    if(!salario){
                                    alert('Por favor, preencha o salario do usuário');
                                    return;
                                    }else{
                                        let usuario_nome = $('#txt_usuario').val();
                                        
                                        if(!usuario_nome){
                                        alert('Por favor, preencha o usuário de acesso do usuário');
                                        return;
                                        }else{
                                            let usuario = {
                                                id: '',
                                                nome: nome,
                                                data_nascimento: data_nascimento,
                                                celular: celular,
                                                cargo: cargo,
                                                senha: senha,
                                                cpf: cpf,
                                                data_admissao: data_admissao,
                                                salario: salario,
                                                usuario_nome: usuario_nome
                                            };
                                            atualizarUsuario(urlId, usuario);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }       
    });
    
    function atualizarUsuario(id, usuario){
        $.ajax({           
           url: 'http://localhost:8080/usuario/atualizar/'+id,
           method: 'PUT',
           contentType: 'application/json',
           data: JSON.stringify({
                    id: id,
                    nome: usuario.nome,
                    data_nascimento: usuario.data_nascimento,
                    celular: usuario.celular,
                    cargo: usuario.cargo,
                    senha: usuario.senha,
                    cpf: usuario.cpf,
                    data_admissao: usuario.data_admissao,
                    salario: usuario.salario,
                    usuario_nome: usuario.usuario_nome
            }),
            error: function(){
                alert('deu erro');
            },
            complete: function(){
                alert('Usuário Atualizado com sucesso!');
                window.location="http://localhost:8080/controleUsuario";
            }
        });        
    }
    
    /*  SCRIPTS GERAIS */
    
    function queryString() {  
              var loc = window.location.href;
              let urlSplit = loc.split('=');
              return urlSplit[1];             
    }
    
    carregarProdutos();
    carregarUsuarios();
});
