package com.energoit.modbus.client;

import com.ghgande.j2mod.modbus.io.ModbusTCPTransaction;
import com.ghgande.j2mod.modbus.msg.ReadMultipleRegistersRequest;
import com.ghgande.j2mod.modbus.msg.ReadMultipleRegistersResponse;
import com.ghgande.j2mod.modbus.msg.WriteSingleRegisterRequest;
import com.ghgande.j2mod.modbus.net.TCPMasterConnection;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;

@Slf4j
public class TCPModbus {
    /* The important instances of the classes mentioned before */
    protected static TCPMasterConnection con = null; //the conection
    protected static ModbusTCPTransaction trans = null; //the transaction
    protected static ReadMultipleRegistersRequest req = null; //the request
    protected static ReadMultipleRegistersResponse res = null; //the response
    /* Variables for storing the parameters */
    protected static InetAddress addr = null; //the slave's address
    protected static final int ref = Integer.parseInt("20", 16); //the reference; offset where to start reading from
    protected static final int count = 1; //the number of DI's to read
    protected static final int repeat = 1; //a loop for repeating the transaction

    public static void callTCPModbus(String address, int port) throws Exception {
        try {
            addr = InetAddress.getByName(address);
            //2. Open the connection
            con = new TCPMasterConnection(addr);
            con.setPort(port);
            con.connect();
            req = new ReadMultipleRegistersRequest(ref, count);
            trans = new ModbusTCPTransaction(con);
            req.setUnitID(1);
            trans.setRequest(req);
            log.info("Trying {}:{}", address, port);
            trans.execute();
            res = (ReadMultipleRegistersResponse) trans.getResponse();
            for (int n = 0; n < res.getWordCount(); n++) {
                System.out.println("Word " + 1 + "=" + res.getRegisterValue(n));
            }
            con.close();
        } catch (Exception ex) {
            log.error("Error", ex);
        }
    }
}