package co.com.yunus.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import co.com.yunus.application.dto.Cliente;

public class MainFile {

	public static void main(String[] args) {
		 List<Cliente> inputList = new ArrayList<>();
		 MainFile mainFile = new MainFile();
		    try{
		      File inputF = new File("C:/Users/David/Downloads/bd.csv");
		      InputStream inputFS = new FileInputStream(inputF);
		      BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
		      inputList = br.lines()
		    		  .skip(1)
		    		  	.map(mainFile.mapToItem)
		    		  	.filter(Objects::nonNull)
		    		  		.filter(c-> StringUtils.isNotEmpty(c.getDocumento()))
		    		  		.collect(Collectors.toList());
		      System.out.println(inputList.size());
		      br.close();
		     
		    } catch (Exception e) {
				e.printStackTrace();
			}
	}
	 
	 private Function<String, Cliente> mapToItem = (line) -> {
		  String[] p = line.split(";(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
		  Cliente cliente = null;
		  cliente = new Cliente();
		  cliente.setNombres((p[0]));
		  cliente.setDocumento((p[1].trim()));

		  return cliente;
		};
}
