package com.example.demo.impl.transaction;

import com.example.demo.api.TransactionApi;
import com.example.demo.core.model.Processor;
import com.example.demo.impl.transaction.context.TransactionContext;
import com.example.demo.impl.transaction.request.TransactionDetailRequest;
import com.example.demo.impl.transaction.result.TransactionDetailResult;

import java.util.List;


public class TransactionApiImpl implements TransactionApi {

    private List<Processor<TransactionContext>> queryTransactionDetailProcessors;

    @Override
    public TransactionDetailResult queryTransactionDetail(TransactionDetailRequest request) {
        TransactionDetailResult result = new TransactionDetailResult();
        TransactionContext context = new TransactionContext(request, result);
        queryTransactionDetailProcessors.forEach(processor -> {
            if (processor.isSkipped(context)) {
                return;
            }
            processor.check(context);
            processor.doProcess(context);

        });
        return (TransactionDetailResult) context.getResult();
    }

    public void setQueryTransactionDetailProcessors(List<Processor<TransactionContext>> queryTransactionDetailProcessors) {
        this.queryTransactionDetailProcessors = queryTransactionDetailProcessors;
    }
}
