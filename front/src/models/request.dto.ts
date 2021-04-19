import { RequestItemDTO } from './request-item.dto';
import { PaymentDTO } from './payment.dto';
import { RefDTO } from './ref.dto';
export interface RequestDTO{
    client: RefDTO
    deliveryAdress: RefDTO
    payment: PaymentDTO
    itens: RequestItemDTO[]
}