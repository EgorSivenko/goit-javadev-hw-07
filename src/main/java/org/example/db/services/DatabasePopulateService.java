package org.example.db.services;

import org.example.db.entity.Client;
import org.example.db.entity.Project;
import org.example.db.entity.ProjectWorker;
import org.example.db.entity.Worker;
import org.example.db.utils.Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class DatabasePopulateService {
    private static final Connection connection = Database.getInstance().getConnection();

    public static boolean addWorkers(List<Worker> workers) throws SQLException {
        connection.setAutoCommit(false);

        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO workers (worker_name, birthday, level, salary) VALUES (?, ?, ?, ?)")) {
            for (Worker worker : workers) {
                ps.setString(1, worker.getName());
                ps.setDate(2, Date.valueOf(worker.getBirthday()));
                ps.setString(3, worker.getLevel().getLabel());
                ps.setInt(4, worker.getSalary());

                ps.addBatch();
            }
            ps.executeBatch();

            connection.commit();
            return true;
        } catch (SQLException e) {
            connection.rollback();
            System.err.println(e.getMessage());
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public static boolean addClients(List<Client> clients) throws SQLException {
        connection.setAutoCommit(false);

        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO clients (client_name) VALUES (?)")) {
            for (Client client : clients) {
                ps.setString(1, client.getName());

                ps.addBatch();
            }
            ps.executeBatch();

            connection.commit();
            return true;
        } catch (SQLException e) {
            connection.rollback();
            System.err.println(e.getMessage());
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public static boolean addProjects(List<Project> projects) throws SQLException {
        connection.setAutoCommit(false);

        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO projects (client_id, start_date, finish_date) VALUES (?, ?, ?)")) {
            for (Project project : projects) {
                ps.setInt(1, project.getClientId());
                ps.setDate(2, Date.valueOf(project.getStartDate()));
                ps.setDate(3, Date.valueOf(project.getFinishDate()));

                ps.addBatch();
            }
            ps.executeBatch();

            connection.commit();
            return true;
        } catch (SQLException e) {
            connection.rollback();
            System.err.println(e.getMessage());
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public static boolean addProjectWorkers(List<ProjectWorker> projectWorkers) throws SQLException {
        connection.setAutoCommit(false);

        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO project_workers (project_id, worker_id) VALUES (?, ?)")) {
            for (ProjectWorker projectWorker : projectWorkers) {
                ps.setInt(1, projectWorker.getProjectId());
                ps.setInt(2, projectWorker.getWorkerId());

                ps.addBatch();
            }
            ps.executeBatch();

            connection.commit();
            return true;
        } catch (SQLException e) {
            connection.rollback();
            System.err.println(e.getMessage());
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public static void main(String[] args) throws SQLException {
        // Test queries with PreparedStatements using data from populate_db.sql

        List<Worker> workers = List.of(
                new Worker("Kent Beatty", LocalDate.of(1986, 9, 15), Worker.WorkerLevel.JUNIOR, 1234),
                new Worker("Holly Dooley", LocalDate.of(1984, 1, 17), Worker.WorkerLevel.JUNIOR, 1234),
                new Worker("Andres Champlin", LocalDate.of(1997, 12, 22), Worker.WorkerLevel.JUNIOR, 1234),
                new Worker("Dale Gerlach", LocalDate.of(1993, 10, 14), Worker.WorkerLevel.JUNIOR, 1234),
                new Worker("Winston Hane", LocalDate.of(1987, 1, 13), Worker.WorkerLevel.JUNIOR, 1234),
                new Worker("Dana Romaguera", LocalDate.of(1974, 3, 16), Worker.WorkerLevel.JUNIOR, 1234),
                new Worker("Lindsay Ullrich", LocalDate.of(1997, 12, 22), Worker.WorkerLevel.JUNIOR, 1234),
                new Worker("Camille Kling", LocalDate.of(1991, 7, 8), Worker.WorkerLevel.JUNIOR, 1234),
                new Worker("Cristina Boyle", LocalDate.of(1984, 11, 22), Worker.WorkerLevel.JUNIOR, 1234),
                new Worker("Lionel Carroll", LocalDate.of(1992, 11, 21), Worker.WorkerLevel.JUNIOR, 1234)
        );

        List<Client> clients = List.of(
                new Client("Kent Beatty"),
                new Client("Holly Dooley"),
                new Client("Andres Champlin"),
                new Client("Dale Gerlach"),
                new Client("Winston Hane"),
                new Client("Dana Romaguera"),
                new Client("Lindsay Ullrich"),
                new Client("Camille Kling"),
                new Client("Cristina Boyle"),
                new Client("Lionel Carroll")
        );

        List<Project> projects = List.of(
                new Project(3, LocalDate.of(2017, 2, 16), LocalDate.of(2021, 10, 2)),
                new Project(2, LocalDate.of(2018, 6, 7), LocalDate.of(2021, 11, 21)),
                new Project(6, LocalDate.of(2017, 6, 22), LocalDate.of(2021, 2, 4)),
                new Project(4, LocalDate.of(2018, 10, 2), LocalDate.of(2020, 11, 28)),
                new Project(5, LocalDate.of(2017, 8, 4), LocalDate.of(2022, 9, 2)),
                new Project(7, LocalDate.of(2018, 2, 3), LocalDate.of(2020, 11, 10)),
                new Project(10, LocalDate.of(2019, 9, 2), LocalDate.of(2022, 11, 9)),
                new Project(3, LocalDate.of(2017, 1, 28), LocalDate.of(2021, 4, 20)),
                new Project(6, LocalDate.of(2018, 2, 12), LocalDate.of(2020, 11, 17)),
                new Project(5, LocalDate.of(2017, 5, 19), LocalDate.of(2022, 6, 1))
        );

        List<ProjectWorker> projectWorkers = List.of(
                new ProjectWorker(1, 3), new ProjectWorker(1, 5), new ProjectWorker(1, 6),
                new ProjectWorker(2, 2), new ProjectWorker(2, 4),
                new ProjectWorker(3, 1),
                new ProjectWorker(4, 6), new ProjectWorker(4, 8),
                new ProjectWorker(5, 1), new ProjectWorker(5, 2),
                new ProjectWorker(6, 3), new ProjectWorker(6, 9), new ProjectWorker(6, 10),
                new ProjectWorker(7, 3),
                new ProjectWorker(8, 2), new ProjectWorker(8, 4), new ProjectWorker(8, 7), new ProjectWorker(8, 10),
                new ProjectWorker(9, 8), new ProjectWorker(9, 9),
                new ProjectWorker(10, 2), new ProjectWorker(10, 5)
        );

        System.out.println("Workers were added: " + addWorkers(workers));
        System.out.println("Clients were added: " + addClients(clients));
        System.out.println("Projects were added: " + addProjects(projects));
        System.out.println("ProjectWorkers were added: " + addProjectWorkers(projectWorkers));

        connection.close();
    }
}
