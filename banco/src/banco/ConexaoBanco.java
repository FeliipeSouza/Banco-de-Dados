package banco;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class ConexaoBanco {
	private Connection connection;
	private ResultSet resultset;
	
	public Connection getConnection() {
		return connection;
	}
	
	 public void setConnection(Connection connection) {
	this.connection = connection;
	}	
	
			
	
public Connection conexao() {
	String url = "jdbc:postgresql://localhost:5432/cadastro";
	String usu = "postgres";
	String senh = "suasenha";
	try {
		Connection connect = DriverManager.getConnection(url,usu,senh);
		if(connect!=null) {
			System.out.println("Conexao estabelecida com sucesso! \n");
		 setConnection(connect);
			return connect;
		}else {System.out.println("Nao conectado");
	
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null; 
}

public void ConsultTabAlun() {
	try {
	ResultSet rst = connection.createStatement().executeQuery("SELECT * FROM aluno");
	if(!rst.next()) {
		System.out.println("Sua tabela esta vazia");}
	else {
		do {
		System.out.println();
		System.out.println("Nome do aluno: " + rst.getString("nome_aluno"));
		System.out.println("CPF: " + rst.getString("cpf_aluno"));
		System.out.println("RG: " + rst.getString("rg_aluno"));
		System.out.println("Nome do Pai: " + rst.getString("nomePai_aluno"));
		System.out.println("Nome da Mae: " + rst.getString("nomeMae_aluno"));
		System.out.println("Matricula: " + rst.getString("matricula"));
} while(rst.next()); }
	} catch (SQLException e) {
		System.out.println("Erro ao consultar");
	}
}

public void ConsultTabCrs() {
	try {
		
	ResultSet rst = connection.createStatement().executeQuery("SELECT * FROM curso");
	if (!rst.next()) {
	    System.out.println("Sua tabela está vazia");}
	else {
	do {
	System.out.println();
	System.out.println("Codigo do curso: " + rst.getString("cod_curso"));
	System.out.println("CPF do aluno inscrito: " + rst.getString("cpf_aluno"));
	System.out.println("Nome do curso: " + rst.getString("nome_curso"));
	System.out.println("Turno: " + rst.getString("turno_curso"));
	System.out.println("Modalidade: " + rst.getString("modalidade_curso"));}
		while(rst.next());
	}
			
		
	} catch (SQLException e) {
		e.printStackTrace();
	}

}

public void inserirAlun() {
	String n,c,r,np,nm,m;
	Scanner scn = new Scanner(System.in);
	System.out.println("Digite o nome do aluno");
	n= scn.nextLine();
	System.out.println("Digite o CPF");
	c= scn.nextLine();
	System.out.println("Digite o RG");
	r= scn.nextLine();
	System.out.println("Digite o nome do pai");
	np = scn.nextLine();
	System.out.println("Digite o nome do mae");
	nm = scn.nextLine();
	System.out.println("Digite a matricula");
	m = scn.nextLine();
	try {
		System.out.println();
		ResultSet rst = connection.createStatement().executeQuery("INSERT INTO aluno"
				+ "(nome_aluno,cpf_aluno,rg_aluno,nomePai_aluno,nomeMae_aluno,matricula)"
				+ " VALUES( '" + n + "','"+ c +"','"+ r +"','"+ np +"','"+ nm +"',"+ m +");");
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

public void inserirCurs() {
	String c,cp,n,t,m;
	Scanner scn = new Scanner(System.in);
	System.out.println("Digite o Codigo do curso");
	c= scn.nextLine();
	System.out.println("Digite o CPF do aluno");
	cp= scn.nextLine();
	System.out.println("Digite o nome do curso");
	n= scn.nextLine();
	System.out.println("Digite o turno");
	t= scn.nextLine();
	System.out.println("Digite a modalidade");
	m= scn.nextLine();
	try {
		System.out.println();
		ResultSet rst = connection.createStatement().executeQuery("INSERT INTO curso"
				+ "(cod_curso,cpf_aluno,nome_curso,turno_curso,modalidade_curso)"
				+ " VALUES( " + c + ",'"+ cp +"','"+ n +"','"+ t +"','"+m+"');");
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
}


public void atualizarAlun() {
	
	String cp,n;
	Scanner scn = new Scanner(System.in);
	System.out.println("Digite um novo nome para o aluno");
	n = scn.nextLine();
	System.out.println("Digite digite o cpf desse aluno");
	cp = scn.nextLine();
	
	String sql = "UPDATE aluno SET nome_aluno = '"+n+"' WHERE cpf_aluno='"+cp+"'";
	try {
		ResultSet rst = connection.createStatement().executeQuery(sql);
	} catch (Exception e) {
	}
}

public void atualizarCurs() {
	String cod,n;
	Scanner scn = new Scanner(System.in);
	System.out.println("Digite um novo nome para o Curso");
	n = scn.nextLine();
	System.out.println("Digite digite o codigo desse curso");
	cod = scn.nextLine();
	String sql = "UPDATE curso SET nome_curso = '"+n+"' WHERE cod_curso ="+cod+"";
	try {
		ResultSet rst = connection.createStatement().executeQuery(sql);
	} catch (Exception e) {
	}
}


public void removerAlun() {
	String cp;
	Scanner scn = new Scanner(System.in);
	
	try {
		System.out.println("Digite digite o CPF do aluno que voce quer excluir");
		cp = scn.nextLine();
		String sql = "DELETE FROM aluno WHERE cpf_aluno='"+cp+"'";
		ResultSet rst = connection.createStatement().executeQuery(sql);
	} catch (Exception e) {
		e.printStackTrace();
	
	}
}

public void removerCurs() {
	String cod;
	Scanner scn = new Scanner(System.in);
	System.out.println("Digite digite o codigo do curso que voce quer excluir");
	cod = scn.nextLine();
	String sql = "DELETE FROM curso WHERE cod_curso="+cod+"";
	try {
		ResultSet rst = connection.createStatement().executeQuery(sql);
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void innerJoin() {
	String sql = "SELECT al.nome_aluno,al.cpf_aluno,cs.nome_curso,cs.modalidade_curso FROM curso cs "
			+ "INNER JOIN aluno al ON al.cpf_aluno = cs.cpf_aluno";
	try {
		ResultSet rst = connection.createStatement().executeQuery(sql);
		if(!rst.next()) {
			System.out.println("Uma ou ambas as tabelas nao possui elementos");}
		else {
		do {
			System.out.println();
		System.out.println("Nome aluno: " + rst.getString("nome_aluno"));
		System.out.println("CPF: " + rst.getString("cpf_aluno"));
		System.out.println("Nome do curso: " + rst.getString("nome_curso"));		
		System.out.println("Modalidade: " + rst.getString("modalidade_curso"));
		}
		while(rst.next());
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void fecharConexao() {
	
	if(connection!=null) {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
}



		
	
	
	


	

