package br.com.zup.edu.delivery.pedido.pedido.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Cliente cliente;

    @ManyToOne(optional = false)
    private Restaurante restaurante;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id")
    private List<Item> itens = new ArrayList<>();

    @ManyToOne(optional = false)
    private Endereco endereco;

    private String observacao;

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;

    @Column(nullable = false)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @UpdateTimestamp
    private LocalDateTime atualizadoEm;
    private LocalDateTime criadoEm;

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate.
     */
    @Deprecated
    public Pedido() {
    }

    public Pedido(Cliente cliente, Restaurante restaurante, List<Item> itens, Endereco endereco, String observacao) {
        this.cliente = cliente;
        this.restaurante = restaurante;
        this.itens = itens;
        this.endereco = endereco;
        this.observacao = observacao;
        this.tipoPagamento = TipoPagamento.DINHEIRO;
        this.status = StatusPedido.PENDENTE_APROVACAO;
        this.criadoEm = LocalDateTime.now();
        calcularTotal();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public List<Item> getItens() {
        return itens;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getObservacao() {
        return observacao;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    private void calcularTotal() {
        this.total = itens.stream()
                .map(Item::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        if(cliente.extrapolou()) {
            this.total.add(calculaValorExtra());
        }
   }

        public void cancelarPedido () {
            if (status == StatusPedido.PENDENTE_APROVACAO) {
                status = StatusPedido.CANCELADO;
                cliente.adicionaCancelamento();
            }
        }

        public BigDecimal calculaValorExtra () {
            return this.total.multiply(new BigDecimal("1.15"));
        }

        public boolean podeSolicitar(){

            LocalDateTime now = LocalDateTime.now();

            return criadoEm.plusMinutes(5).isBefore(now);
        }
        //A atualização só é permitida para pedidos diferentes de Cancelado e Entregue.
     // PENDENTE, APROVADO, CANCELADO, FAZENDO, FEITO, TRANSPORTE, ENTREGUE
    public void atualizaStatus() {
        if (status == StatusPedido.CANCELADO || status == StatusPedido.ENTREGUE){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"nao é possivel atualizar este status");

        } else if (status == StatusPedido.PENDENTE_APROVACAO){
            status = StatusPedido.APROVADO;

        } else if (status == StatusPedido.APROVADO){
            status = StatusPedido.FAZENDO;

        }else if (status == StatusPedido.FAZENDO){
            status = StatusPedido.FEITO;

        } else if (status == StatusPedido.FEITO){
            status = StatusPedido.TRANSPORTE;

        } else if (status == StatusPedido.TRANSPORTE){
            status = StatusPedido.ENTREGUE;
        }
    }
}


