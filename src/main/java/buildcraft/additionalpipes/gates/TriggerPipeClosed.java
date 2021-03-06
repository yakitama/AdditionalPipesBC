package buildcraft.additionalpipes.gates;

import buildcraft.additionalpipes.pipes.PipeBehaviorClosed;
import buildcraft.additionalpipes.textures.Textures;
import buildcraft.additionalpipes.utils.Log;
import buildcraft.api.core.render.ISprite;
import buildcraft.api.statements.IStatement;
import buildcraft.api.statements.IStatementContainer;
import buildcraft.api.statements.IStatementParameter;
import buildcraft.api.statements.ITriggerInternal;
import buildcraft.transport.tile.TilePipeHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TriggerPipeClosed extends APTrigger implements ITriggerInternal {

	public TriggerPipeClosed()
	{
		super("pipe_closed");
	}

	@Override
	public boolean isTriggerActive(IStatementContainer statement, IStatementParameter[] parameters)
	{
		PipeBehaviorClosed closedPipe = null;
		//this much casting feels unsafe
		try
		{
			closedPipe = (PipeBehaviorClosed) ((TilePipeHolder)statement.getTile()).getPipe().getBehaviour();
		}
		catch(RuntimeException ex)
		{
			Log.error("Failed to get reference to Closed Pipe:");
			ex.printStackTrace();
			return false;
		}
		
		// if the first ItemStack is null, then there are no items in the pipe and the trigger should be inactive
		return closedPipe.isClosed();
	}

	@Override
	public int maxParameters() 
	{
		return 0;
	}

	@Override
	public int minParameters() 
	{
		return 0;
	}

	@Override
	public IStatementParameter createParameter(int index) 
	{
		return null;
	}

	@Override
	public IStatement rotateLeft() 
	{
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ISprite getSprite()
	{
	    return Textures.TRIGGER_PIPE_CLOSED;
	}

	@Override
	public IStatement[] getPossible()
	{
		return new IStatement[0];
	}


}
