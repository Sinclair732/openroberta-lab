package de.fhg.iais.roberta.persistence.connector;

import java.io.Serializable;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.fhg.iais.roberta.dbc.Assert;

/**
 * class for wrapping a hibernate session. This class eases the use of sessions. It creates transactions, after commits a new transaction is created
 * automatically. Closing a session forces a commit.<br>
 * <b>If neither close nor commit are called for this wrapper objects, changes of the database w.r.t. to the wrappped session are <i>not persisted</i>!</b>
 * 
 * @author rbudde
 */
public class SessionWrapper {
    private static final Logger LOG = LoggerFactory.getLogger(SessionWrapper.class);
    private Session session;

    /**
     * wrap a hibernate session. Package visible: may only be called from {@link SessionFactoryWrapper}
     * 
     * @param session the hibernate session to be wrapped
     */
    SessionWrapper(Session session) {
        LOG.info("open session + start transaction");
        this.session = session;
        this.session.beginTransaction();
    }

    /**
     * rollback the current transaction
     */
    public void rollback() {
        LOG.info("rollback");
        this.session.getTransaction().rollback();
    }

    /**
     * commit the current transaction and start a new one
     */
    public void commit() {
        LOG.info("commit + start transaction");
        this.session.getTransaction().commit();
        this.session.beginTransaction();
    }

    /**
     * commit the current transaction and close the session
     */
    public void close() {
        LOG.info("close session (after commit)");
        this.session.getTransaction().commit();
        this.session.close();
        this.session = null;
    }

    /**
     * @return the hibernate session wrapped by this object. Be careful!
     */
    public Session getSession() {
        Assert.notNull(this.session);
        return this.session;
    }

    /**
     * create a HQL query
     * 
     * @param query the SQL query
     * @return the Query object
     */
    public Query createQuery(String query) {
        return this.session.createQuery(query);
    }

    /**
     * persist a business object in the underlying database.
     * 
     * @param toBePersisted the business object to be persisted
     * @return the persisted object
     */
    public Serializable save(Object toBePersisted) {
        return this.session.save(toBePersisted);
    }

    public void delete(Object object) {
        this.session.delete(object);
        this.session.flush();
    }
}
