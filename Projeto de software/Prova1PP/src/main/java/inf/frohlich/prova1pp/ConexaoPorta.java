package inf.frohlich.prova1pp;

import com.fazecast.jSerialComm.SerialPort;
import org.springframework.stereotype.Component;


@Component
public class ConexaoPorta {

    private SerialPort serialPort;

    public void conectar() {
        try {
            String portaCOM = "COM3";
            int taxa = 9600;

            serialPort = SerialPort.getCommPort(portaCOM);
            serialPort.setBaudRate(taxa);
            serialPort.setNumDataBits(8);
            serialPort.setNumStopBits(SerialPort.ONE_STOP_BIT);
            serialPort.setParity(SerialPort.NO_PARITY);

            if (serialPort.openPort()) {
                System.out.println("Porta aberta com sucesso");
            } else {
                System.out.println("Erro ao abrir porta");
            }

        } catch (Exception e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
    }

    public void enviaDados(char opcao) {
        try {
            if (serialPort == null || !serialPort.isOpen()) {
                conectar();
            }

            byte[] dados = new byte[]{(byte) opcao};
            serialPort.writeBytes(dados, dados.length);

        } catch (Exception e) {
            System.out.println("Erro ao enviar: " + e.getMessage());
        }
    }
}