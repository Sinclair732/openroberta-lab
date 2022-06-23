package de.fhg.iais.roberta.syntax.sensors.arduino.nano33blesense;

import java.util.List;

import de.fhg.iais.roberta.blockly.generated.Block;
import de.fhg.iais.roberta.blockly.generated.Value;
import de.fhg.iais.roberta.syntax.Phrase;
import de.fhg.iais.roberta.syntax.lang.expr.Expr;
import de.fhg.iais.roberta.syntax.sensor.BuiltinSensor;
import de.fhg.iais.roberta.transformer.Ast2Jaxb;
import de.fhg.iais.roberta.transformer.Jaxb2Ast;
import de.fhg.iais.roberta.transformer.Jaxb2ProgramAst;
import de.fhg.iais.roberta.transformer.forClass.NepoBasic;
import de.fhg.iais.roberta.util.ast.BlocklyBlockProperties;
import de.fhg.iais.roberta.util.ast.BlocklyComment;
import de.fhg.iais.roberta.util.syntax.BlocklyConstants;

@NepoBasic(name = "LPS22HB_PRESSURE", category = "SENSOR", blocklyNames = {"robsensors_lps22hb_pressure_getDataAvailableSample"})
public final class Lps22hbPressureSensor<V> extends BuiltinSensor<V> {

    public final Expr<V> pressure;

    public Expr<V> getPressure() {
        return pressure;
    }

    private Lps22hbPressureSensor(BlocklyBlockProperties properties, BlocklyComment comment, Expr<V> pressure) {
        super(properties, comment, null);
        this.pressure = pressure;
        setReadOnly();
    }

    public static <V> Lps22hbPressureSensor<V> make(BlocklyBlockProperties properties, BlocklyComment comment, Expr<V> pressure) {
        return new Lps22hbPressureSensor<>(properties, comment, pressure);
    }

    /**
     * Transformation from JAXB object to corresponding AST object.
     *
     * @param block for transformation
     * @param helper class for making the transformation
     * @return corresponding AST object
     */
    public static <V> Phrase<V> jaxbToAst(Block block, Jaxb2ProgramAst<V> helper) {
        List<Value> values = Jaxb2Ast.extractValues(block, (short) 1);
        Expr<V> pressure = helper.getVar(values, BlocklyConstants.VARIABLE_VALUE);
        return Lps22hbPressureSensor.make(Jaxb2Ast.extractBlockProperties(block), Jaxb2Ast.extractComment(block), pressure);
    }

    @Override
    public Block astToBlock() {
        Block block = new Block();
        Ast2Jaxb.setBasicProperties(this, block);
        Ast2Jaxb.addValue(block, BlocklyConstants.VARIABLE_VALUE, this.pressure);
        return block;
    }
}