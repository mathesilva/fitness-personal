package com.mfaias.fitnesspersonal.controller;

import com.mfaias.fitnesspersonal.dto.request.PedidoRequestDTO;
import com.mfaias.fitnesspersonal.entity.Pedido;
import com.mfaias.fitnesspersonal.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;


    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody PedidoRequestDTO request){
            Pedido pedido = pedidoService.fecharPedido(request);
            return ResponseEntity.status (HttpStatus.CREATED).body(pedido);

    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos(){
        List<Pedido> pedido = pedidoService.listPedidos();
        return ResponseEntity.ok(pedido);

    }
}
