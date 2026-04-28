package com.mfaias.fitnesspersonal.service;

import com.mfaias.fitnesspersonal.dto.request.PedidoRequestDTO;
import com.mfaias.fitnesspersonal.entity.ItemPedido;
import com.mfaias.fitnesspersonal.entity.Pedido;
import com.mfaias.fitnesspersonal.entity.Product;
import com.mfaias.fitnesspersonal.exceptions.EstoqueInsuficienteException;
import com.mfaias.fitnesspersonal.repository.PedidoRepository;
import com.mfaias.fitnesspersonal.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductRepository productRepository;

    public PedidoService(PedidoRepository pedidoRepository, ProductRepository productRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productRepository = productRepository;
    }


    @Transactional
    public Pedido fecharPedido(PedidoRequestDTO request){
        Pedido pedido = new Pedido();
        pedido.setNomeClient(request.nomeClient());

        BigDecimal valorTotal = BigDecimal.ZERO;
        List<ItemPedido> itens = new ArrayList<>();

        for ( PedidoRequestDTO.Item itemRequest : request.produtos()){
            Product product = productRepository.findById(itemRequest.produtoId()).orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + itemRequest.produtoId()));

            if (product.getQuantidadeEstoque() < itemRequest.quantidade()){
                throw new EstoqueInsuficienteException("Estoque Insuficiente para o produto: " + product.getNome());
            }

            product.setQuantidadeEstoque(product.getQuantidadeEstoque() - itemRequest.quantidade());

            BigDecimal subtotal = product.getValor().multiply(new BigDecimal(itemRequest.quantidade()));
            valorTotal = valorTotal.add(subtotal);

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setProduct(product);
            itemPedido.setPedido(pedido);
            itemPedido.setQuantidadeComprada(itemRequest.quantidade());
            itens.add(itemPedido);

        }
        pedido.setValorTotal(valorTotal);
        pedido.setItens(itens);
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listPedidos(){
        List<Pedido> pedidoList = pedidoRepository.findAll();
        return pedidoList;
    }
}
