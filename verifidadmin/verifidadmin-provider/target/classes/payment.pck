create
or replace package payment is

  -- Author  : Momomtajul Karim
  -- Created : 04-Sep-2025 14:24:30
  -- Purpose : 
  

  -- Public function and procedure declarations
    PROCEDURE update_payment_status(
        i_id IN TRANSACTION_REGISTER.ID%TYPE,
        i_state IN NUMBER,
        i_batch_no IN TRANSACTION_REGISTER.BATCH_NO%TYPE,
        i_tracer_no IN TRANSACTION_REGISTER.TRACER_NO%TYPE,
        i_remarks IN TRANSACTION_REGISTER.REMARKS%TYPE
    );

end payment;
/
create
or replace package body payment is



    PROCEDURE update_payment_status(
        i_id IN TRANSACTION_REGISTER.ID%TYPE,
        i_state IN NUMBER,
        i_batch_no IN TRANSACTION_REGISTER.BATCH_NO%TYPE,
        i_tracer_no IN TRANSACTION_REGISTER.TRACER_NO%TYPE,
        i_remarks IN TRANSACTION_REGISTER.REMARKS%TYPE
    ) IS
BEGIN
UPDATE TRANSACTION_REGISTER t
SET state       = i_state,
    t.batch_no  = i_batch_no,
    t.tracer_no = i_tracer_no,
    t.remarks   = i_remarks
WHERE id = i_id;
END update_payment_status;
  -- Initialization
end payment;
/
