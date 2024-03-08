$(document).ready(function(){
    
    /*  SCRIPTS DE LOGIN */
    
    $('#formLogin').submit(function(event){
        event.preventDefault();
        let usuario_nome = $('#txt_usuario_nome').val();
        
        if(!usuario_nome){
            alert('Por favor, preencha o nome de usuário');
            return;
        }else{
            let senha = $('#txt_senha').val();
            if(!senha){
                alert('Por favor, preencha a senha do usuário');
                return;
            }else{
                let usuario_login = {
                    usuario_nome: usuario_nome,
                    senha: senha
                };
                autenticaUsuario(usuario_login);
            }
        }   
    });    
    
    function autenticaUsuario(usuario_login){
        $.get('http://localhost:8080/usuario/listar', function(data){
            let encontrado = false;
            for(let i = 0; i < data.length; i++){
                let usuario = data[i];
                
                if(usuario.usuario_nome == usuario_login.usuario_nome &&
                        usuario.senha == usuario_login.senha){
                    let usuarioLogado = {
                        usuario_nome: usuario_login.usuario_nome,
                        cargo: usuario.cargo
                    };
                    encontrado = true;
                    gravaSessao(usuarioLogado);
                }
            }
            if(encontrado == false){
                alert("Usuário incorreto!");
            }
        
        });
    }
    
    function gravaSessao(usuario_sessao){
        window.location.href = "http://localhost:8080/gravaSession?nome="+usuario_sessao.usuario_nome+"&cargo="+ usuario_sessao.cargo; 
    }
    
    function verificaPermissao(){
           $.get('http://localhost:8080/sessoes/leCargo', function(data){
            let cargo = data;
            
            if(cargo == "" && window.location.href != "http://localhost:8080/"){
                alert("Sem sessão! \nPor favor realize o login!");
                window.location.href = "http://localhost:8080/";
                
            }else{
                
                pegaNome();

                if(cargo == "vendedor"){
                    $('#header_link_Cestoque').removeAttr('href');

                    $('#header_link_Cusuario').removeAttr('href');
                    $('#header_link_Lusuario').removeAttr('href');

                    $('#a_link_usuario').removeAttr('href');
                    $('#link_usuario').prop('disabled', true);


                    $('.btn_editar_venda').removeAttr("href");
                    $('.btn_deletar_venda').removeAttr("href");

                    $('.btn_editar_usuario').removeAttr("href");
                    $('.btn_deletar_usuario').removeAttr("href");

                    $('.btn_editar_produto').removeAttr("href");
                    $('.btn_deletar_produto').removeAttr("href");
                }
            }
        });
    }
    
    function pegaNome(){
           $.get('http://localhost:8080/sessoes/le', function(data){
               $('.nomeLogado').text("Olá, " + data);
        });
    }
    
    
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
                            .addClass('bi bi-pencil');
                    let botaoDeletar = $('<i>')
                            .addClass('bi bi-x-lg');
                    
                    let aBotaoAtualizar = $('<a>')
                            .addClass('btn_editar_produto')
                            .attr('href', 'editarProduto/id='+produto.id)
                            .append(botaoAtualizar);
                    let aBotaoDeletar = $('<a>')
                            .addClass('btn_deletar_produto')
                            .attr('href', 'deletarProduto/id='+produto.id)
                            .append(botaoDeletar);
                    
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
                verificaPermissao();
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
                            .addClass('bi bi-pencil');
                    let botaoDeletar = $('<i>')
                            .addClass('bi bi-x-lg');
                    
                    let aBotaoAtualizar = $('<a>')
                            .addClass('btn_editar_usuario')
                            .attr('href', 'editarUsuario/id='+usuario.id)
                            .append(botaoAtualizar);
                    let aBotaoDeletar = $('<a>')
                            .addClass('btn_deletar_usuario')
                            .attr('href', 'deletarUsuario/id='+usuario.id)
                            .append(botaoDeletar);
                    
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
                verificaPermissao();
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
    
    /*  SCRIPTS VENDA */
    
    function carregarVendas(){
        
        $.get('http://localhost:8080/venda/listar', function(data){
                $('#tabelaVendas tbody').empty();
                
                for(let i = 0; i < data.length; i++){
                    let venda = data[i];
                    let id = $('<td>').text(venda.id);
                    let valor = $('<td>').text(venda.valor_total);
                    let hora_venda = $('<td>').text(venda.hora_venda);
                    let numero_parcelas = $('<td>').text(venda.numero_parcelas);
                    let data_venda = $('<td>').text(venda.data_venda);
                    let forma_pagamento = $('<td>').text(venda.forma_pagamento);
                    let botaoAtualizar = $('<i>')
                            .addClass('bi bi-pencil');                       
                    let botaoDeletar = $('<i>')
                            .addClass('bi bi-x-lg');
                    let aBotaoAtualizar = $('<a>')
                            .addClass("btn_editar_venda")
                            .attr("href", "editarVenda/id="+venda.id)
                            .append(botaoAtualizar);
                    console.log("ligou");
                    let aBotaoDeletar = $('<a>')
                            .addClass("btn_deletar_venda")
                            .attr("href", "deletarVenda/id="+venda.id)
                            .append(botaoDeletar);
                    
                    let tdBotaoAtualizar = $('<td>').append(aBotaoAtualizar);
                    let tdBotaoDeletar = $('<td>').append(aBotaoDeletar);

                    let tr = $('<tr>')
                            .attr('data-id', venda.id)
                            .append(id)
                            .append(valor)
                            .append(hora_venda)
                            .append(numero_parcelas)
                            .append(data_venda)
                            .append(forma_pagamento)
                            .append(tdBotaoAtualizar)
                            .append(tdBotaoDeletar);
                    $('#tabelaVendas tbody').append(tr);
                }
                verificaPermissao();
        });
    }
    
    function criarVenda(venda){
        $.ajax({
            url: 'http://localhost:8080/venda/adicionar',
            method: 'POST',
            contentType: "application/json",
            data: JSON.stringify(venda),
            error: function(data){
                alert("Não foi possível cadastrar a venda no API!");
                console.log(data);
            },            
            complete: function(){                   
                alert('Venda Cadastrada com sucesso!');
                window.location="http://localhost:8080/controleVenda";
            }            
        });
    }
    
    $('#formCriarVenda').submit(function(event){
        event.preventDefault();
        
        let valor = $('#txt_valor').val();
        if(!valor){
            alert('Por favor, preencha o valor da venda');
            return;
        }else{
            let hora_venda = $('#txt_hora_venda').val();
            
            if(!hora_venda){
            alert('Por favor, preencha a hora da venda');
            return;
            }else{                
                let numero_parcelas = $('#txt_numero_parcelas').val();
                
                if(!numero_parcelas){
                alert('Por favor, preencha o número de parcelas da venda');
                return;
                }else{                    
                    let data_venda = $('#txt_data_venda').val();
                    
                    if(!data_venda){
                    alert('Por favor, preencha a data da venda');
                    return;
                    }else{
                        let forma_pagamento = $('#txt_forma_pagamento').val();
                        let venda = {
                            id: '',
                            valor_total: valor,
                            hora_venda: hora_venda,
                            numero_parcelas: numero_parcelas,
                            data_venda: data_venda,
                            forma_pagamento: forma_pagamento
                        };
                        criarVenda(venda);
                    }
                }
            }
        }         
    });
    
    $('#formEditarVenda').submit(function(event){
        event.preventDefault();
        let urlId = queryString();
        let valor = $('#txt_valor').val();
        if(!valor){
            alert('Por favor, preencha o valor da venda');
            return;
        }else{
            let hora_venda = $('#txt_hora_venda').val();
            
            if(!hora_venda){
            alert('Por favor, preencha a hora da venda');
            return;
            }else{                
                let numero_parcelas = $('#txt_numero_parcelas').val();
                
                if(!numero_parcelas){
                alert('Por favor, preencha o número de parcelas da venda');
                return;
                }else{                    
                    let data_venda = $('#txt_data_venda').val();
                    
                    if(!data_venda){
                    alert('Por favor, preencha a data da venda');
                    return;
                    }else{
                        
                        let forma_pagamento = $('#txt_forma_pagamento').val();
                        let venda = {
                            id: '',
                            valor_total: valor,
                            hora_venda: hora_venda,
                            numero_parcelas: numero_parcelas,
                            data_venda: data_venda,
                            forma_pagamento: forma_pagamento
                        };
                        atualizarVenda(urlId, venda);
                    }
                }
            }
        }
    });
    
    function atualizarVenda(id, venda){
        $.ajax({           
           url: 'http://localhost:8080/venda/atualizar/'+id,
           method: 'PUT',
           contentType: 'application/json',
           data: JSON.stringify({
                   id: id,
                   valor_total: venda.valor_total,
                   hora_venda: venda.hora_venda,
                   numero_parcelas: venda.numero_parcelas,
                   data_venda: venda.data_venda,
                   forma_pagamento: venda.forma_pagamento
            }),
            error: function(){
                alert('deu erro');
            },
            complete: function(){
                alert('Venda Atualizada com sucesso!');
                window.location="http://localhost:8080/controleVenda";
            }
        });        
    }
    
    /*  SCRIPTS GERAIS */
    
    function queryString() {  
              var loc = window.location.href;
              let urlSplit = loc.split('=');
              return urlSplit[1];             
    }
    
    function carregarDados(){
        var loc = window.location.href;
        let urlSplit = loc.split('/');
        switch(urlSplit[3]){
            case "controleProduto":
                carregarProdutos();
                break;
            case "controleUsuario":
                carregarUsuarios();
                break;
            case "controleVenda":
                carregarVendas();
                break;
        }
    }
    carregarDados();
    verificaPermissao();
});

