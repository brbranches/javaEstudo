package service;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class RelatorioService implements Serializable {

	private static final long serialVersionUID = 1L;

	// DEfinindo uma String final em que ela recebe o caminho que representa os
	// relat�rios.
	private static final String FOLDER_RELATORIOS = "/relatorios";

	// Definindo subrelat�rios, quando tem relacionamento de 1 para muitos (1-*).
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";

	// Definindo o separador para trabalhar com arquivos. S�o as barras \ ou /,
	// dependend do sistema operacional.
	private String SEPARATOR = File.separator;
	private static String CaminhoArquivoRelatorio = null;
	private JRExporter exporter = null;
	private String caminhoSubReport_Dir = "";
	private File arquivoGerado = null;

	// Vai receber uma lista de dados que vai servir de base para gerar o relat�rio
	public String gerarRelatorio(List<?> ListaDataBeanCollection, HashMap parametrosRelatorio,
			String nomeRelatorioJasper, String nomeRelatorioSaida, ServletContext servletContext) throws Exception {

		/*
		 * Cria a lista de colelctionDataSource de beans que carregam os dados para
		 * montar o relat�rio.
		 */
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(ListaDataBeanCollection);

		/* Fornece o caminho fisico at� a pasta que contem os relat�rios .jasper */
		String caminhoRelatorio = servletContext.getRealPath(FOLDER_RELATORIOS);

		File file = new File(caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper");

		if (caminhoRelatorio == null || caminhoRelatorio != null && caminhoRelatorio.isEmpty() || !file.exists()) {
			caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
			SEPARATOR = "";
		}

		/*Caminho para imagens*/
		parametrosRelatorio.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);
		
		/*caminho completo at� o relat�rio compilado indicado*/
		String caminhoArquivosJasper = caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper";
		
		/*Faz o carregamento do relat�rio*/
		JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivosJasper);
		
		
		/*Setando os par�metros SUBREPORT_DIR com o caminho fisico para subreport*/
		caminhoSubReport_Dir = caminhoRelatorio + SEPARATOR;
		parametrosRelatorio.put(SUBREPORT_DIR, caminhoSubReport_Dir);
		
		/*Carregando o arquivo para a mem�ria, passo o relat�rio, os parametros e os dados da lista*/
		JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio, jrbcds);
		
		exporter = new JRPdfExporter();
		
		/*Caminho do relat�rio exportado*/
		CaminhoArquivoRelatorio = caminhoRelatorio + SEPARATOR + nomeRelatorioSaida + ".pdf";
		
		/*Criando novo arquivo exportado*/
		arquivoGerado = new File(CaminhoArquivoRelatorio);
		
		/*Prepara a impress�o*/
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
		
		/*Dando nome de sa�da para o relat�rio*/
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
		
		/*Executando a exporta��o do arquivo*/
		exporter.exportReport();
		
		/*Remove o arquivo do servidor ap�s ser feito o download, para nao pesar o sistema*/
		arquivoGerado.deleteOnExit();
		
		return CaminhoArquivoRelatorio;
		

	}

}
