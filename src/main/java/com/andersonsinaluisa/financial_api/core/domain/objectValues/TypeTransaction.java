package com.andersonsinaluisa.financial_api.core.domain.objectValues;

public enum TypeTransaction {
    INGRESO("INGRESO"),         // Transacciones que representan ingresos de dinero
    GASTO("GASTO"),             // Transacciones que representan gastos de dinero
    TRANSFERENCIA("TRANSFERENCIA"), // Movimientos entre cuentas propias
    PAGO("PAGO"),               // Pago hacia proveedores o servicios
    RETIRO("RETIRO"),           // Retiro de dinero de una cuenta
    DEPOSITO("DEPOSITO"),       // Depósito de dinero en una cuenta
    REEMBOLSO("REEMBOLSO"),      // Devolución de dinero, por ejemplo, reembolsos
    AJUSTE("AJUSTE");           // Ajustes manuales de balance

    private final String value;

    // Constructor
    private TypeTransaction(String value) {
        this.value = value;
    }

    // Getter
    public String getValue() {
        return value;
    }

    // Método estático para validar el tipo de transacción
    public static boolean isValidType(String transactionType) {
        for (TypeTransaction type : TypeTransaction.values()) {
            if (type.getValue().equals(transactionType)) {
                return true;
            }
        }
        return false;
    }

}